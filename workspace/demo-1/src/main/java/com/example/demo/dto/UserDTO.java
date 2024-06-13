package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	
	private Long id;

	@JsonProperty("userId")
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String userId;
	
	@JsonProperty("password")
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[a-z])(?=.*[0-9]).{8,16}$", message = "비밀번호는 8~16자 영문 소문자, 숫자를 조합하여 입력하세요.")
	private String password;

	@JsonProperty("password2")
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[a-z])(?=.*[0-9]).{8,16}$", message = "비밀번호는 8~16자 영문 소문자, 숫자를 조합하여 입력하세요.")
	private String password2;
	
	@JsonProperty("name")
	@NotBlank(message = "이름은 필수 입력 값입니다.")
	private String name;
	
	@JsonProperty("birth")
	@NotBlank(message = "생년월일은 필수 입력 값입니다.")
	private String birth;
	
	@JsonProperty("email")
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
	private String email;
	
	@JsonProperty("addr1")
	private String addr1;
	
	@JsonProperty("addr2")
	private String addr2;

	@JsonProperty("kakaoId")
	private String kakaoId;

}
