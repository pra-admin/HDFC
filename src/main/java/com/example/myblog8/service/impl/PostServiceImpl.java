package com.example.myblog8.service.impl;

import com.example.myblog8.entity.Post;
import com.example.myblog8.exception.ResourceNotFoundException;
import com.example.myblog8.payload.PostDto;
import com.example.myblog8.repository.PostRepository;
import com.example.myblog8.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){

        this.postRepository=postRepository;
    }


    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);

        PostDto dto = mapToDto(savedPost);

        return dto;
    }

    @Override
    public List<PostDto> getAllPost(){
        List<Post> posts = postRepository.findAll();
        List<PostDto> dto = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public PostDto getPostById(int id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found with this id", "id", id)
        );
        return mapToDto(post);

    }

    @Override
    public void deleteById(int id){
        postRepository.deleteById(id);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("not found with this id", "id", id)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savedPost = postRepository.save(post);
       return mapToDto(savedPost);

    }

    Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }

    PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }
}
