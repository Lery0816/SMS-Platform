package com.lbyqsl.common.exception;

import com.lbyqsl.common.enums.ExceptionEnums;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private Integer code;

    public ApiException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ApiException(ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMsg());
        this.code = exceptionEnums.getCode();
    }



}
