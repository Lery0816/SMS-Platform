package com.lbyqsl.api.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.lbyqsl.api.filter.Checkfilter;
import com.lbyqsl.api.utils.PhoneFormatCheckUtil;
import com.lbyqsl.common.enums.ExceptionEnums;
import com.lbyqsl.common.exception.ApiException;
import com.lbyqsl.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "mobile")
@Slf4j
public class MobileCheckFilter implements Checkfilter {

    @Override
    public void Check(StandardSubmit submit) {
        String mobile = submit.getMobile();
        if(!StringUtils.isEmpty(mobile) && PhoneFormatCheckUtil.isChinaPhone(mobile)){
            // 如果校验进来，代表手机号么得问题
            log.info("【接口模块-校验手机号】   手机号格式合法 mobile = {}",mobile);
            return;
        }
        log.info("【接口模块-校验手机号】   手机号格式不正确 mobile = {}",mobile);
        throw new ApiException(ExceptionEnums.ERROR_MOBILE);
    }
}
