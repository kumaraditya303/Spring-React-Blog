package io.github.kumaraditya303.blog.controller;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.kumaraditya303.blog.dto.PostDto;
import io.github.kumaraditya303.blog.entity.Post;
import io.github.kumaraditya303.blog.entity.User;
import io.github.kumaraditya303.blog.repository.PostRepository;
import io.github.kumaraditya303.blog.service.DBFileStorageService;
import io.github.kumaraditya303.blog.util.ApiResponse;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final DBFileStorageService dbFileStorageService;

    private final PostRepository postRepository;

    @Autowired
    public PostController(DBFileStorageService dbFileStorageService, PostRepository postRepository) {
        this.dbFileStorageService = dbFileStorageService;
        this.postRepository = postRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult result,
            Authentication authentication) throws IOException {
        ApiResponse response = new ApiResponse();
        if (result.hasErrors()) {
            response.setError(result.getFieldErrors().stream()
                    .collect(toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)));
            return new ResponseEntity<>(response.getData(), HttpStatus.BAD_REQUEST);
        }
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setOverview(postDto.getOverview());
        post.setContent(postDto.getContent());
        post.setFeatured(postDto.getFeatured());
        post.setAuthor((User) authentication.getPrincipal());
        post.setThumbnail(dbFileStorageService.getFile(postDto.getThumbnail()));
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updatePost(@PathVariable Long id, @Valid @RequestBody PostDto postDto,
            BindingResult result, Authentication authentication) {
        ApiResponse response = new ApiResponse();
        if (result.hasFieldErrors()) {
            response.setError(result.getFieldErrors().stream()
                    .collect(toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage)));
            return new ResponseEntity<>(response.getData(), HttpStatus.BAD_REQUEST);
        }
        if (postRepository.existsById(id)
                && postRepository.findById(id).get().getAuthor().getId() == ((User) authentication.getPrincipal()).getId()) {
            Post post = postRepository.findById(id).get();
            post.setTitle(postDto.getTitle());
            post.setOverview(postDto.getOverview());
            post.setContent(postDto.getContent());
            post.setFeatured(postDto.getFeatured());
            post.setThumbnail(dbFileStorageService.getFile(postDto.getThumbnail()));
            return new ResponseEntity<>(postRepository.save(post), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deletePost(@PathVariable Long id, Authentication authentication) {
        if (postRepository.existsById(id)
                && postRepository.findById(id).get().getAuthor().getId() == ((User) authentication.getPrincipal()).getId()) {
            postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
