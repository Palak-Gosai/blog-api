package com.palak.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	
	
	private int id;
	@NotEmpty
	private String categoryTitle;
	@NotEmpty
	private String categoryDescription;

}
