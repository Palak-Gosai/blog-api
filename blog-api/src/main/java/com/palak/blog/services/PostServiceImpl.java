package com.palak.blog.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.palak.blog.entity.Category;
import com.palak.blog.entity.Post;
import com.palak.blog.entity.User;
import com.palak.blog.exception.ResouceNotFoundException;
import com.palak.blog.payloads.PostDto;
import com.palak.blog.payloads.PostResponse;
import com.palak.blog.payloads.UserDto;
import com.palak.blog.repository.CategoryRepo;
import com.palak.blog.repository.PostRepo;
import com.palak.blog.repository.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	UserRepo userRepo;

	@Override
	public PostDto createPost(PostDto postDto, int categoryId, int userId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResouceNotFoundException("Category", "id", categoryId));

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResouceNotFoundException("User", "userId", userId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setPostImage("def.png");
		post.setCategory(category);
		post.setUser(user);
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int id) {
		Post post = this.postRepo.findById(id).orElseThrow(()-> new ResouceNotFoundException("post", "PostId", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setPostImage(postDto.getPostImage());
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(int id) {
		
		Post post = this.postRepo.findById(id).orElseThrow(()-> new ResouceNotFoundException("post", "PostId", id));
		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPosts(int pageNumber,int pageSize,String sortBy,String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePosts = this.postRepo.findAll(page);
		
		List<Post> posts = pagePosts.getContent();

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(int id) {

		Post post = this.postRepo.findById(id).orElseThrow(()-> new ResouceNotFoundException("post","psotId",id));	
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) {

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResouceNotFoundException("category", "categoryId", categoryId));

		List<Post> posts = this.postRepo.findByCategory(cat);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResouceNotFoundException("user", "userId", userId));

		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
