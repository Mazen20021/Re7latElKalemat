package com.example.wordsjourney.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class userDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String picture;
    private Long score;
}
