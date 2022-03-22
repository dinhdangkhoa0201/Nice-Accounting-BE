package com.example.mybackend.controller;

import com.example.mybackend.requests.CategoryRequest;
import com.example.mybackend.requests.CriteriaRequest;
import com.example.mybackend.services.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/category")
@CrossOrigin(origins = "*")
@Slf4j
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody CategoryRequest request) {
        log.info("[save]");
        return new ResponseEntity<>(categoryService.save(request), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody CategoryRequest request) {
        log.info("[update]");
        return new ResponseEntity<>(categoryService.update(id, request), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("[delete]");
        return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        log.info("[findById]");
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/findByCriteria", method = RequestMethod.POST)
    public ResponseEntity<?> findByCriteria(@RequestBody CriteriaRequest request) {
        log.info("[findByCriteria]");
        return new ResponseEntity<>(categoryService.findByCriteria(request), HttpStatus.OK);
    }

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        log.info("[findAll]");
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/count", method = RequestMethod.GET)
    public ResponseEntity<?> countAll() {
        log.info("[countAll]");
        return new ResponseEntity<>(categoryService.countAll(), HttpStatus.OK);
    }
}
