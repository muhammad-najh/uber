package com.skysoft.krd.uber.advices;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class APIResponse<T> {
    private LocalDateTime localDateTime;
    private T data;
    private ApiError error;

    public APIResponse(ApiError error) {
        this();
        this.error = error;
    }

    public APIResponse(T data) {
        this();
        this.data = data;
    }

    public APIResponse(){
        localDateTime = LocalDateTime.now();
    }
}
