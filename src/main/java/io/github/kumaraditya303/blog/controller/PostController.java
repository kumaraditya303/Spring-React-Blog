package io.github.kumaraditya303.blog.controller;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.kumaraditya303.blog.dto.PostDto;
import io.github.kumaraditya303.blog.entity.Post;
import io.github.kumaraditya303.blog.entity.User;
import io.github.kumaraditya303.blog.repository.PostRepository;
import io.github.kumaraditya303.blog.service.DBFileStorageService;
import io.github.kumaraditya303.blog.util.ApiResponse;

@RestController
public class PostController {

    private final DBFileStorageService dbFileStorageService;

    private final PostRepository postRepository;

    public PostController(DBFileStorageService dbFileStorageService, PostRepository postRepository) {
        this.dbFileStorageService = dbFileStorageService;
        this.postRepository = postRepository;
    }

    @PostMapping(value = "/api/post/create")
    public ResponseEntity<Object> createImage(@Valid @RequestBody PostDto postDto, BindingResult result,
            Authentication author) throws IOException {
        ApiResponse response = new ApiResponse();
        if (result.hasErrors()) {
            response.setError(result.getFieldErrors().stream()
                    .collect(toMap(FieldError::getField, field -> field.getDefaultMessage())));
            return new ResponseEntity<>(response.getData(), HttpStatus.BAD_REQUEST);
        }
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setOverview(postDto.getOverview());
        post.setContent(postDto.getContent());
        post.setFeatured(postDto.getFeatured());
        post.setAuthor((User) author.getPrincipal());
        post.setThumbnail(dbFileStorageService.getFile(postDto.getThumbnail()));
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }
}
