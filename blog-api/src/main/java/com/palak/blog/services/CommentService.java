package com.palak.blog.services;

import com.palak.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, int postId);
	
	void deleteComment(int commentId);

}
