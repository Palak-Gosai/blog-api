package com.palak.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.palak.blog.config.AppConstants;
import com.palak.blog.payloads.APIResponse;
import com.palak.blog.payloads.PostDto;
import com.palak.blog.payloads.PostResponse;
import com.palak.blog.services.FileService;
import com.palak.blog.services.PostService;



@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	FileService fileService;
	
	@Value("${project.image}")
	private String imagePath;
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("userId") int userId,@PathVariable("categoryId") int categoryId){
		PostDto createPost = this.postService.createPost(postDto, categoryId, userId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	//Get By category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int categoryId){
		
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getpostByCategory(@PathVariable int userId){
		
		List<PostDto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required =false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required =  false) int pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
		
		PostResponse posts = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int id){
		PostDto postDto = this.postService.getPostById(id);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{id}")
	public APIResponse deletePost(@PathVariable int id) {
		
		this.postService.deletePost(id);
		return new APIResponse("Post deleted",true);
	}
	
	@PutMapping("/post/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable int id){
		
		PostDto updatedPost = this.postService.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
		
	}
	
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
		
		List<PostDto> result = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam MultipartFile image,@PathVariable int postId){
		
		//we can write this code during post save no need to write separate code for it.
		// to do add this code into post create api.
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(imagePath, image);
		
		postDto.setPostImage(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
		
	}
}
