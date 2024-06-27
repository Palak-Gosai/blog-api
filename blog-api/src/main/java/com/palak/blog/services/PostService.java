package com.palak.blog.services;

import java.util.List;

import com.palak.blog.payloads.PostDto;
import com.palak.blog.payloads.PostResponse;

public interface PostService {
	
	
	//create
	public PostDto createPost(PostDto postDto,int categoryId,int userId);
	
	//update
	public PostDto updatePost(PostDto postDto,int id);
	
	
	//delete
	public void deletePost(int id);
	
	//get all
	public PostResponse getAllPosts(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	//get by id
	public PostDto getPostById(int id);
	
	//get all by category
	public List<PostDto> getPostByCategory(int categoryId);
	
	//get all by user
	public List<PostDto> getPostByUser(int userId);
	
	//search post
	public List<PostDto> searchPost(String keyword);

}
