package com.example.mybackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class IndexController {

    @RequestMapping(path = "/check-health", method = RequestMethod.GET)
    public ResponseEntity<?> checkHealth() {
        log.info("[checkHealth]");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
