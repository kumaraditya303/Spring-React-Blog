package io.github.kumaraditya303.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kumaraditya303.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
