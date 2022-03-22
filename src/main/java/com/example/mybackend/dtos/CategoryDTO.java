package com.example.mybackend.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO extends AbstractBaseDTO implements Serializable {
    private String name;

    private String desc;
}
