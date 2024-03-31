package com.example.demo.dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class LoginDTO {
    private String userId;
    private String password;
}
