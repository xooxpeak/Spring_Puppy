package com.example.demo.dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@Data
public class LoginDTO {
    private String userId;
    private String password;
}
