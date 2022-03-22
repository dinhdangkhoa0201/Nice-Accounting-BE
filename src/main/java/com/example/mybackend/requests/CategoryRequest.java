package com.example.mybackend.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryRequest extends AbstractBaseRequest implements Serializable {
    private String name;

    private String desc;
}
