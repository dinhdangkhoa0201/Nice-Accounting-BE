package com.example.mybackend.repositories.customs.impls;

import com.example.mybackend.entities.ArticleEntity;
import com.example.mybackend.models.DataTableDomain;
import com.example.mybackend.repositories.customs.AbstractRepositoryCustom;
import com.example.mybackend.repositories.customs.ArticleRepositoryCustom;

import javax.persistence.Query;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ArticleRepositoryCustomImpl extends AbstractRepositoryCustom implements ArticleRepositoryCustom {

    private String buildCriteria(Map<String, Object> criteria, String[] orderBy) {
        StringBuilder sb = new StringBuilder();
        sb.append(" WHERE 1=1 ");
        if (criteria.containsKey("code")) {
            sb.append(" AND code = :code ");
        }
        if (criteria.containsKey("name")) {
            sb.append(" AND name = :name ");
        }

        if (orderBy.length > 0) {
            StringBuilder sorting = new StringBuilder();
            for (String str : orderBy) {
                sorting.append(",").append(str);
            }
            sb.append(" order by ").append(sorting.substring(1));
        }

        return sb.toString();
    }

    @Override
    public DataTableDomain<ArticleEntity> findByCriteria(Map<String, Object> criteria, String[] orderBy, int page, int perPage) {
        String selectSql = "SELECT a FROM ArticleEntity a ";

        Query query = entityManager.createQuery(selectSql + buildCriteria(criteria, orderBy), ArticleEntity.class);
        for (String key : criteria.keySet()) {
            Object value = criteria.get(key);
            query.setParameter(key, value);
        }

        DataTableDomain<ArticleEntity> dataTable = new DataTableDomain<>();
        dataTable.setObjects(query.setFirstResult(page * perPage).setMaxResults(perPage).getResultList());
        dataTable.setIndex(page);
        dataTable.setLength(perPage);

        String countSql = "SELECT COUNT(a) FROM ArticleEntity a ";
        query = entityManager.createQuery(countSql + buildCriteria(criteria, orderBy), Long.class);
        for (String key : criteria.keySet()) {
            Object value = criteria.get(key);
            query.setParameter(key, value);
        }
        dataTable.setTotal(Integer.parseInt(query.getSingleResult().toString()));

        return dataTable;
    }

    @Override
    public Long count(Map<String, Object> criteria, String[] orderBy) {
        String countSql = "SELECT COUNT(a) FROM ArticleEntity a ";
        Query query = entityManager.createQuery(countSql + buildCriteria(criteria, new String[]{}), Long.class);
        for (String key : criteria.keySet()) {
            Object value = criteria.get(key);
            query.setParameter(key, value);
        }
        return Long.parseLong(query.getSingleResult().toString());
    }
}
