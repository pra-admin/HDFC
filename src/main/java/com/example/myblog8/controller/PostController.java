package com.example.myblog8.controller;

import com.example.myblog8.payload.PostDto;
import com.example.myblog8.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    @PostMapping("/save")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable("id") int id){
        return postService.getPostById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id){
        postService.deleteById(id);
        return new ResponseEntity<>("Post deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") int id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
