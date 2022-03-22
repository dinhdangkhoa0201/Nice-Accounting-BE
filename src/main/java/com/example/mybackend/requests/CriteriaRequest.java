package com.example.mybackend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaRequest implements Serializable {
    private LinkedHashMap<String, Object> criteria;

    private String[] orderBy;

    private int page;

    private int perPage;
}
