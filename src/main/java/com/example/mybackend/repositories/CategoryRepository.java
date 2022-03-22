package com.example.mybackend.repositories;

import com.example.mybackend.entities.CategoryEntity;
import com.example.mybackend.repositories.customs.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>, CategoryRepositoryCustom {
}
