package com.lbyqsl.api.advice;

import com.lbyqsl.api.utils.R;
import com.lbyqsl.api.vo.ResultVO;
import com.lbyqsl.common.exception.ApiException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHander {

    @ExceptionHandler(ApiException.class)
    public ResultVO apiException(ApiException ex){

        return R.error(ex);
    }
}
