package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResDTO {
    private String message;
    private String code;
    private Object data;
    private Object data2;
}
