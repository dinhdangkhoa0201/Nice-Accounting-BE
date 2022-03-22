package com.example.mybackend.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class AbstractBaseDTO implements Serializable {
    private Long id;

    private String createBy;

    private LocalDateTime createDate;

    private String updateBy;

    private LocalDateTime updateDate;
}
