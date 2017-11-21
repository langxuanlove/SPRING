package com.javaniu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.javaniu.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	public Post findByUserUserName(@Param("userName") String userName);

	public Post findByTitle(@Param("title") String title);

}
