package com.example.mybackend.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleDTO extends AbstractBaseDTO implements Serializable {
    private String code;

    private String name;

    private String desc;

    private String content;

    private CategoryDTO category;
}
