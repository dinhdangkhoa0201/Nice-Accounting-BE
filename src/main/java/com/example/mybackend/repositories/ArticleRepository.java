package com.example.mybackend.repositories;

import com.example.mybackend.entities.ArticleEntity;
import com.example.mybackend.repositories.customs.ArticleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>, ArticleRepositoryCustom {
    List<ArticleEntity> findByCategoryId(Long categoryId);
}
