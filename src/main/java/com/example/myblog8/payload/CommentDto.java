package com.example.myblog8.payload;

import lombok.Data;

@Data
public class CommentDto {

    private int id;
    private String name;
    private String email;
    private String body;
}
