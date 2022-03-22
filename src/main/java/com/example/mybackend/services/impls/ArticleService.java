package com.example.mybackend.services.impls;

import com.example.mybackend.dtos.ArticleDTO;
import com.example.mybackend.dtos.CategoryDTO;
import com.example.mybackend.entities.ArticleEntity;
import com.example.mybackend.entities.CategoryEntity;
import com.example.mybackend.enums.ResponseMessage;
import com.example.mybackend.enums.ResponseStatus;
import com.example.mybackend.models.DataTableDomain;
import com.example.mybackend.repositories.ArticleRepository;
import com.example.mybackend.repositories.CategoryRepository;
import com.example.mybackend.requests.ArticleRequest;
import com.example.mybackend.requests.CriteriaRequest;
import com.example.mybackend.responses.ResponseResult;
import com.example.mybackend.services.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService implements IArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseResult<ArticleDTO> save(ArticleRequest request) {
        log.info("[save] -> Save Article");
        ResponseResult<ArticleDTO> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            ArticleEntity entity = new ArticleEntity();
            BeanUtils.copyProperties(request, entity);
            Optional<CategoryEntity> categoryOption = categoryRepository.findById(request.getCategory().getId());
            if (categoryOption.isPresent()) {
                entity.setCategory(categoryOption.get());
                entity = articleRepository.save(entity);
            }
            if (entity.getId() != null) {
                ArticleDTO dto = new ArticleDTO();
                BeanUtils.copyProperties(entity, dto);
                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, dto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult<ArticleDTO> update(Long id, ArticleRequest request) {
        log.info("[update] -> Update Article");
        ResponseResult<ArticleDTO> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            Optional<ArticleEntity> optional = articleRepository.findById(id);
            if (optional.isPresent()) {
                ArticleEntity entity = optional.get();
                BeanUtils.copyProperties(request, entity);
                entity = articleRepository.save(entity);

                ArticleDTO dto = new ArticleDTO();
                BeanUtils.copyProperties(entity, dto);
                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, dto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }


    @Override
    public ResponseResult<Boolean> delete(Long id) {
        log.info("[delete] -> Delete Article");
        ResponseResult<Boolean> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            Optional<ArticleEntity> optional = articleRepository.findById(id);
            if (optional.isPresent()) {
                articleRepository.deleteById(id);
                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, Boolean.TRUE);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult<ArticleDTO> findById(Long id) {
        log.info("[findById] -> FindById Article");
        ResponseResult<ArticleDTO> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            Optional<ArticleEntity> optional = articleRepository.findById(id);
            if (optional.isPresent()) {
                ArticleEntity entity = optional.get();
                ArticleDTO dto = new ArticleDTO();
                BeanUtils.copyProperties(entity, dto);

                CategoryEntity categoryEntity = entity.getCategory();
                CategoryDTO categoryDTO = new CategoryDTO();
                BeanUtils.copyProperties(categoryEntity, categoryDTO);
                dto.setCategory(categoryDTO);
                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, dto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult<List<ArticleDTO>> findAll() {
        return null;
    }

    @Override
    public ResponseResult<List<ArticleDTO>> findByCriteria(CriteriaRequest criteria) {
        log.info("[findByCriteria] -> FindByCriteria Article");
        ResponseResult<List<ArticleDTO>> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            DataTableDomain<ArticleEntity> tableDomain = articleRepository.findByCriteria(criteria.getCriteria(), criteria.getOrderBy(), criteria.getPage() - 1, criteria.getPerPage());
            if (tableDomain != null && !CollectionUtils.isEmpty(tableDomain.getObjects())) {
                List<ArticleDTO> listDTO = new ArrayList<>();
                listDTO = tableDomain.getObjects().stream().map(e -> {
                    ArticleDTO dto = new ArticleDTO();
                    BeanUtils.copyProperties(e, dto);

                    CategoryEntity categoryEntity = e.getCategory();
                    CategoryDTO categoryDTO = new CategoryDTO();
                    BeanUtils.copyProperties(categoryEntity, categoryDTO);
                    dto.setCategory(categoryDTO);
                    return dto;
                }).collect(Collectors.toList());
                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, listDTO);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult<List<ArticleDTO>> findByCategoryId(Long categoryId) {
        log.info("[findByCategoryId] -> FindByCategoryId Article");
        ResponseResult<List<ArticleDTO>> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            List<ArticleEntity> list = articleRepository.findByCategoryId(categoryId);
            if (!CollectionUtils.isEmpty(list)) {
                List<ArticleDTO> listDTO = new ArrayList<>();
                listDTO = list.stream().map(e -> {
                    ArticleDTO dto = new ArticleDTO();
                    BeanUtils.copyProperties(e, dto);

                    return dto;
                }).collect(Collectors.toList());
                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, listDTO);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult<Long> countAll() {
        log.info("[countAll] -> CountAll Article");
        ResponseResult<Long> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, articleRepository.count());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
