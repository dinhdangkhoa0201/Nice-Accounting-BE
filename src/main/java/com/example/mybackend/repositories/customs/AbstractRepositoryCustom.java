package com.example.mybackend.repositories.customs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepositoryCustom {
    @PersistenceContext
    protected EntityManager entityManager;
}
