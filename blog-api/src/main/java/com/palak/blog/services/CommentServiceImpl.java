package com.palak.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palak.blog.entity.Comment;
import com.palak.blog.entity.Post;
import com.palak.blog.exception.ResouceNotFoundException;
import com.palak.blog.payloads.CommentDto;
import com.palak.blog.repository.CommentRepo;
import com.palak.blog.repository.PostRepo;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	PostRepo postRepo;
	
	@Autowired
	CommentRepo commentRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResouceNotFoundException("post", "postId", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment saved = this.commentRepo.save(comment);
		return this.modelMapper.map(saved, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResouceNotFoundException("comment", "commentId", commentId));
		this.commentRepo.delete(com);
	}

}
