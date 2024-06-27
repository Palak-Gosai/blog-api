package com.palak.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palak.blog.entity.Comment;

@Repository
public interface CommentRepo  extends JpaRepository<Comment, Integer>{

}
