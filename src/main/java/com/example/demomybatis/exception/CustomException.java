package com.example.demomybatis.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @liuhongchun
 * @Date 2019/9/16  11:26
 * @des 自定义异常
 */
@Data
@Builder
public class CustomException  extends  RuntimeException{
    private static final long serialVersionUID = -9042873134326604599L;

    private String message;
    private int code = 500;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public CustomException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public CustomException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public CustomException(String message, int code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }

}
