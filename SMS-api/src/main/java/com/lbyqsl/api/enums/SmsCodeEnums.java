package com.lbyqsl.api.enums;

import lombok.Getter;

@Getter
public enum SmsCodeEnums {

    PARAMETER_ERROR(-10,"参数不合法");

    private Integer code;
    private String msg;

    SmsCodeEnums(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
