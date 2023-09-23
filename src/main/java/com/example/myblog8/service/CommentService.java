package com.example.myblog8.service;

import com.example.myblog8.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(int postId,  CommentDto commentDto);
}
