package com.example.mybackend.responses;

import com.example.mybackend.enums.ResponseMessage;
import com.example.mybackend.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    private ResponseStatus status;
    private ResponseMessage message;
    private T object;

    public ResponseResult(ResponseStatus status) {
        this.status = status;
    }
}
