package com.example.myblog8.service;

import com.example.myblog8.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto savePost(PostDto postDto);

    List<PostDto> getAllPost();

    PostDto getPostById(int id);

    public void deleteById(int id);

    PostDto updatePost(PostDto postDto, int id);
}
