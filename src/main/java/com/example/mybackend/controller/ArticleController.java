package com.example.mybackend.controller;

import com.example.mybackend.requests.ArticleRequest;
import com.example.mybackend.requests.CriteriaRequest;
import com.example.mybackend.services.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/article")
@CrossOrigin(origins = "*")
@Slf4j
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody ArticleRequest request) {
        log.info("[save]");
        return new ResponseEntity<>(articleService.save(request), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ArticleRequest request) {
        log.info("[update]");
        return new ResponseEntity<>(articleService.update(id, request), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("[delete]");
        return new ResponseEntity<>(articleService.delete(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("[findById]");
        return new ResponseEntity<>(articleService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/findByCriteria", method = RequestMethod.POST)
    public ResponseEntity<?> findByCriteria(@RequestBody CriteriaRequest request) {
        log.info("[findByCriteria]");
        return new ResponseEntity<>(articleService.findByCriteria(request), HttpStatus.OK);
    }

    @RequestMapping(path = "/category/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> findByCategoryId(@PathVariable Long categoryId) {
        log.info("[findByCategoryId]");
        return new ResponseEntity<>(articleService.findByCategoryId(categoryId), HttpStatus.OK);
    }

    @RequestMapping(path = "/count", method = RequestMethod.GET)
    public ResponseEntity<?> countAll() {
        log.info("[countAll]");
        return new ResponseEntity<>(articleService.countAll(), HttpStatus.OK);
    }
}
