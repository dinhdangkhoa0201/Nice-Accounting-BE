package com.example.mybackend.models;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataTableDomain<T> implements Serializable {
    private List<T> objects;
    private int total;
    private int index;
    private int length;
}
