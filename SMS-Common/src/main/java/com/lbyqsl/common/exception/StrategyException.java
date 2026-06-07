package com.lbyqsl.common.exception;

import com.lbyqsl.common.enums.ExceptionEnums;

public class StrategyException extends RuntimeException {

  private Integer code;

  public StrategyException(String message, Integer code) {
    super(message);
    this.code = code;
  }

  public StrategyException(ExceptionEnums enums) {
    super(enums.getMsg());
    this.code = enums.getCode();
  }
}
