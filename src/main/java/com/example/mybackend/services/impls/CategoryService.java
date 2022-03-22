package com.example.mybackend.services.impls;

import com.example.mybackend.dtos.CategoryDTO;
import com.example.mybackend.entities.CategoryEntity;
import com.example.mybackend.enums.ResponseMessage;
import com.example.mybackend.enums.ResponseStatus;
import com.example.mybackend.models.DataTableDomain;
import com.example.mybackend.repositories.CategoryRepository;
import com.example.mybackend.requests.CategoryRequest;
import com.example.mybackend.requests.CriteriaRequest;
import com.example.mybackend.responses.ResponseResult;
import com.example.mybackend.services.ICategoryService;
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
public class CategoryService implements ICategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseResult<CategoryDTO> save(CategoryRequest request) {
        log.info("[save] -> Save Category");
        ResponseResult<CategoryDTO> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            CategoryEntity entity = new CategoryEntity();
            BeanUtils.copyProperties(request, entity);
            entity = categoryRepository.save(entity);
            if (entity.getId() != null) {
                CategoryDTO dto = new CategoryDTO();
                BeanUtils.copyProperties(entity, dto);
                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, dto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult<CategoryDTO> update(Long id, CategoryRequest request) {
        log.info("[update] -> Update Category");
        ResponseResult<CategoryDTO> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            Optional<CategoryEntity> optional = categoryRepository.findById(id);
            if (optional.isPresent()) {
                CategoryEntity entity = optional.get();
                BeanUtils.copyProperties(request, entity);
                entity = categoryRepository.save(entity);

                CategoryDTO dto = new CategoryDTO();
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
        log.info("[delete] -> Delete Category");
        ResponseResult<Boolean> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            Optional<CategoryEntity> optional = categoryRepository.findById(id);
            if (optional.isPresent()) {
                categoryRepository.deleteById(id);

                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, Boolean.TRUE);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult<CategoryDTO> findById(Long id) {
        log.info("[findById] -> FindById Category");
        ResponseResult<CategoryDTO> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            Optional<CategoryEntity> optional = categoryRepository.findById(id);
            if (optional.isPresent()) {
                CategoryEntity entity = optional.get();
                CategoryDTO dto = new CategoryDTO();
                BeanUtils.copyProperties(entity, dto);
                result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, dto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseResult<List<CategoryDTO>> findAll() {
        log.info("[findAll] -> FindAll Category");
        ResponseResult<List<CategoryDTO>> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            List<CategoryEntity> listEntity = categoryRepository.findAll();
            if (!CollectionUtils.isEmpty(listEntity)) {
                List<CategoryDTO> listDTO = new ArrayList<>();
                listDTO = listEntity.stream().map(e -> {
                    CategoryDTO dto = new CategoryDTO();
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
    public ResponseResult<List<CategoryDTO>> findByCriteria(CriteriaRequest request) {
        log.info("[findByCriteria] -> FindByCriteria Category");
        ResponseResult<List<CategoryDTO>> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            DataTableDomain<CategoryEntity> tableDomain = categoryRepository.findByCriteria(request.getCriteria(), request.getOrderBy(), request.getPage() - 1, request.getPerPage());
            if (tableDomain != null && !CollectionUtils.isEmpty(tableDomain.getObjects())) {
                List<CategoryDTO> listDTO = new ArrayList<>();
                listDTO = tableDomain.getObjects().stream().map(e -> {
                    CategoryDTO dto = new CategoryDTO();
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
        log.info("[countAll] -> CountAll Category");
        ResponseResult<Long> result = new ResponseResult<>(ResponseStatus.FAILURE);
        try {
            result = new ResponseResult<>(ResponseStatus.SUCCESS, ResponseMessage.SUCCESS, categoryRepository.count());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
