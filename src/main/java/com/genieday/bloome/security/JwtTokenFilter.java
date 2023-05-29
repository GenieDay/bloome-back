package com.genieday.bloome.security;

import com.genieday.bloome.user.User;
import com.genieday.bloome.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Token꺼내기
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        Boolean flag = true;
        // Token없으면 에러 리턴
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Jwt Token만 분리하기
        final String token = authorizationHeader.split(" ")[1].trim();

        if(token.equals("0.0.0")){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    "visitor", null, List.of(new SimpleGrantedAuthority("USER"))
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
            flag = false;
        }

        if (flag){
            // Valid한지 확인 하기
            if (JwtTokenUtil.isExpired(token, key)) {
                filterChain.doFilter(request, response);
                return;
            }

            // Claim에서 UserName꺼내기
            String idName = JwtTokenUtil.getIdName(token, key);

            // User가져오기
            User user = userService.userExist(idName);

            // UserName넣기
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getIdName(), null, List.of(new SimpleGrantedAuthority("USER"))
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        }
    }
}
