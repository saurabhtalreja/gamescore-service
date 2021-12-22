package com.saurabh.spring.top_score.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "USERSCORE")
@NoArgsConstructor
@AllArgsConstructor
public class UserScore {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    String name;

    @NotNull(message = "Score is mandatory")
    @Column(name = "score")
    Integer score;





    @Override
    public String toString() {
        return "User Score [name=" + name + ", score=" + score + "]";
    }
}
