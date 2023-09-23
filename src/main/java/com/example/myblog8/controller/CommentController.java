package com.example.myblog8.controller;

import com.example.myblog8.payload.CommentDto;
import com.example.myblog8.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable int postId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto comment_Dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment_Dto, HttpStatus.CREATED);
    }

}
