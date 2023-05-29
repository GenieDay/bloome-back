package com.genieday.bloome.survey;

import com.genieday.bloome.user.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "t_others_survey")
public class OthersSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "others_survey_id")
    private Long id;

    @Column(name = "word_1")
    private Integer word1;

    @Column(name = "word_2")
    private Integer word2;

    @Column(name = "word_3")
    private Integer word3;

    @Column(name = "word_4")
    private Integer word4;

    @Column(name = "word_5")
    private Integer word5;

    @Column(name = "word_6")
    private Integer word6;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "leaves")
    private Integer leaves;
}
