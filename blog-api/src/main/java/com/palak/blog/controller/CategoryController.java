package com.palak.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.palak.blog.payloads.APIResponse;
import com.palak.blog.payloads.CategoryDto;
import com.palak.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	
	@Autowired
	CategoryService categoryService;
	
	//create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		
		CategoryDto category=  this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.CREATED);
		
	}
	
	//update category
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("catId") int catId){
		
		CategoryDto updatedCat = this.categoryService.updateCategory(categoryDto, catId);
		
		return new ResponseEntity<CategoryDto>(updatedCat,HttpStatus.OK);
	}
	
	
	//delete category
	@DeleteMapping("/{catId}")
	public ResponseEntity<APIResponse> deleteCategory(@PathVariable("catId") int catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<APIResponse>(new APIResponse("Category deleted Successfully!!",true),HttpStatus.OK);
		
		
	}
	
	//get category by id
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("catId") int catId){
		CategoryDto cat = this.categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryDto>(cat,HttpStatus.OK);
	}
	
	//get all categories 
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> cats = this.categoryService.getAllCategories();
		return ResponseEntity.ok(cats);
	}

}
