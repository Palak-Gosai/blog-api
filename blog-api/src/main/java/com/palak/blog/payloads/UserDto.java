package com.palak.blog.payloads;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	
	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="User name not found")
	private String name;
	@Email(message="email not found")

	private String email;
	@NotNull
	@Size(min=4,message="password not found")
	private String password;
	@NotNull
	@Size(min=4,message="about not found")
	private String about;
	
}
