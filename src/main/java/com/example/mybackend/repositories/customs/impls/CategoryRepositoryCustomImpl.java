package com.example.mybackend.repositories.customs.impls;

import com.example.mybackend.entities.CategoryEntity;
import com.example.mybackend.models.DataTableDomain;
import com.example.mybackend.repositories.customs.AbstractRepositoryCustom;
import com.example.mybackend.repositories.customs.CategoryRepositoryCustom;

import javax.persistence.Query;
import java.util.Map;

@SuppressWarnings("unchecked")
public class CategoryRepositoryCustomImpl extends AbstractRepositoryCustom implements CategoryRepositoryCustom {

    private String buildCriteria(Map<String, Object> criteria, String[] orderBy) {
        StringBuilder sb = new StringBuilder();

        sb.append(" WHERE 1=1 ");
        if (criteria.containsKey("name")) {
            sb.append(" AND name LIKE %:name% ");
        }
        if (criteria.containsKey("desc")) {
            sb.append(" AND desc LIKE %:desc% ");
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
    public DataTableDomain<CategoryEntity> findByCriteria(Map<String, Object> criteria, String[] orderBy, int page, int perPage) {
        String selectSql = "SELECT e FROM CategoryEntity e ";

        Query query = entityManager.createQuery(selectSql + buildCriteria(criteria, orderBy), CategoryEntity.class);
        for (String key : criteria.keySet()) {
            Object value = criteria.get(key);
            query.setParameter(key, value);
        }

        DataTableDomain<CategoryEntity> dataTable = new DataTableDomain<>();
        dataTable.setObjects(query.setFirstResult(page * perPage).setMaxResults(perPage).getResultList());
        dataTable.setIndex(page);
        dataTable.setLength(perPage);

        String countSql = "SELECT COUNT(e) FROM CategoryEntity e ";
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
        String countSql = "SELECT COUNT(e) FROM CategoryEntity e ";
        Query query = entityManager.createQuery(countSql + buildCriteria(criteria, new String[]{}), Long.class);
        for (String key : criteria.keySet()) {
            Object value = criteria.get(key);
            query.setParameter(key, value);
        }
        return Long.parseLong(query.getSingleResult().toString());
    }
}
