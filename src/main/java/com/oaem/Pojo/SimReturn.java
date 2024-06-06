package com.oaem.Pojo;

import lombok.Data;

@Data
public class SimReturn {
    private int code;
    private String message;
    private  Object data;

    public SimReturn(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
