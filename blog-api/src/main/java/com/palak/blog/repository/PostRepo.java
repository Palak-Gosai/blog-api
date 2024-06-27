package com.palak.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palak.blog.entity.Category;
import com.palak.blog.entity.Post;
import com.palak.blog.entity.User;

import java.util.List;


@Repository
public interface PostRepo  extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);

}
