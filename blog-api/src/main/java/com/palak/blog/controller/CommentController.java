package com.palak.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palak.blog.payloads.APIResponse;
import com.palak.blog.payloads.CommentDto;
import com.palak.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable int postId){
	
		CommentDto commments = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(commments,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<APIResponse> deleteComment(@PathVariable int commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<APIResponse>(new APIResponse("this comment deleted successfully",true),HttpStatus.OK);
	}

}
