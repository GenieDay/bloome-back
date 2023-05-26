package com.genieday.bloome.user;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "id_name", length = 20)
    private String idName;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_type", length = 20)
    private UserType userType;

    @Column(name = "name", length = 10)
    private String name;
}
