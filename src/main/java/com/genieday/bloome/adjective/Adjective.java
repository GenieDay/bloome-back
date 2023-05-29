package com.genieday.bloome.adjective;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "t_adjective")
public class Adjective {
    @Id
    @Column(name = "adj_id")
    private Integer id;

    @Column(name = "word", length = 20)
    private String word;
}
