package com.example.mybackend.services;

import com.example.mybackend.dtos.ArticleDTO;
import com.example.mybackend.requests.ArticleRequest;
import com.example.mybackend.requests.CriteriaRequest;
import com.example.mybackend.responses.ResponseResult;

import java.util.List;

public interface IArticleService {
    ResponseResult<ArticleDTO> save(ArticleRequest request);

    ResponseResult<ArticleDTO> update(Long id, ArticleRequest request);

    ResponseResult<Boolean> delete(Long id);

    ResponseResult<ArticleDTO> findById(Long id);

    ResponseResult<List<ArticleDTO>> findAll();

    ResponseResult<List<ArticleDTO>> findByCriteria(CriteriaRequest criteria);

    ResponseResult<List<ArticleDTO>> findByCategoryId(Long categoryId);

    ResponseResult<Long> countAll();
}
