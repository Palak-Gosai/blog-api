package com.palak.blog.payloads;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private String title;
	
	private String content;
	
	private Date addedDate;
	
	private String postImage;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comment;

}
