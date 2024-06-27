package com.palak.blog.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palak.blog.entity.Category;
import com.palak.blog.exception.ResouceNotFoundException;
import com.palak.blog.payloads.CategoryDto;
import com.palak.blog.repository.CategoryRepo;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		this.categoryRepo.save(cat);
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResouceNotFoundException("Category", "CategoryId", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedCat = this.categoryRepo.save(cat);

		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResouceNotFoundException("Category", "CategoryId", categoryId));

		this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResouceNotFoundException("Category","categoryId",categoryId));
		
		return this.modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> cats = this.categoryRepo.findAll();
		
		List<CategoryDto> categories = cats.stream().map((cat)-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		return  categories;
	}

}
