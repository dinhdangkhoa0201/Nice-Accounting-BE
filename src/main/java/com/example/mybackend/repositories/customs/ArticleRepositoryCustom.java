package com.example.mybackend.repositories.customs;

import com.example.mybackend.entities.ArticleEntity;
import com.example.mybackend.models.DataTableDomain;

import java.util.Map;

public interface ArticleRepositoryCustom {
    DataTableDomain<ArticleEntity> findByCriteria(Map<String, Object> criteria, String[] orderBy, int page, int perPage);

    Long count(Map<String, Object> criteria, String[] orderBy);
}
