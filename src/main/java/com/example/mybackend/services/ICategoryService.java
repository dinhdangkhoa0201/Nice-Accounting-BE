package com.example.mybackend.services;

import com.example.mybackend.dtos.CategoryDTO;
import com.example.mybackend.requests.CategoryRequest;
import com.example.mybackend.requests.CriteriaRequest;
import com.example.mybackend.responses.ResponseResult;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    ResponseResult<CategoryDTO> save(CategoryRequest request);

    ResponseResult<CategoryDTO> update(Long id, CategoryRequest request);

    ResponseResult<Boolean> delete(Long id);

    ResponseResult<CategoryDTO> findById(Long id);

    ResponseResult<List<CategoryDTO>> findAll();

    ResponseResult<List<CategoryDTO>> findByCriteria(CriteriaRequest request);

    ResponseResult<Long> countAll();
}
