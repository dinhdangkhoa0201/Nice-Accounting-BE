package com.example.mybackend.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleRequest extends AbstractBaseRequest implements Serializable {
    private String code;

    private String name;

    private String desc;

    private String content;

    private CategoryRequest category;
}
