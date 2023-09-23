package com.example.myblog8.service.impl;

import com.example.myblog8.entity.Comment;
import com.example.myblog8.entity.Post;
import com.example.myblog8.exception.ResourceNotFoundException;
import com.example.myblog8.payload.CommentDto;
import com.example.myblog8.repository.CommentRepository;
import com.example.myblog8.repository.PostRepository;
import com.example.myblog8.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository){
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
    }

    @Override
    public CommentDto createComment(int postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Id", "id", postId)
        );
        comment.setPost(post);
        Comment savedComments = commentRepository.save(comment);
       return mapToDto(savedComments);

    }

    Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return null;
    }
}
