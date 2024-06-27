package com.palak.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResouceNotFoundException extends RuntimeException{
	
	
	String resourceName;
	String fieldName;
	long filedValue;
	public ResouceNotFoundException(String resourceName, String fieldName, long filedValue) {
		super(String.format("%s Not Found with  %s : %s ", resourceName,fieldName,filedValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.filedValue = filedValue;
	}
	
	
	

}
