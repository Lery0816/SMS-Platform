package com.lbyqsl.api.filter.impl;

import com.lbyqsl.api.client.CacheClient;
import com.lbyqsl.api.filter.Checkfilter;
import com.lbyqsl.common.constant.ApiConstant;
import com.lbyqsl.common.constant.CacheConstant;
import com.lbyqsl.common.enums.ExceptionEnums;
import com.lbyqsl.common.exception.ApiException;
import com.lbyqsl.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

@Service(value = "sign")
@Slf4j
public class SignCheckFilter implements Checkfilter {

    private static final Object CLIENT_SIGN_INFO = "signInfo";
    private final int SIGN_START_INDEX=1;
    private final String SIGN_ID = "id";

    @Autowired
    private CacheClient cacheClient;

    @Override
    public void Check(StandardSubmit submit) {
        log.info("【Api模块-ip校验】ip{}校验合法");

        String text=submit.getText();
        //先判断短信内容内是否包含[]，以及是否有内容
        if (!text.contains(ApiConstant.SIGN_PREFIX)||!text.contains(ApiConstant.SIGN_SUFFIX)){
            log.info("【Api模块-sign校验】sign校验不合法,不包含[]");
            throw new ApiException(ExceptionEnums.ERROR_SIGN);
        }

        //获取[]里面的签名
        String realSign=text.substring(SIGN_START_INDEX ,text.indexOf(ApiConstant.SIGN_SUFFIX));
        if (ObjectUtils.isEmpty(realSign)){
            log.info("【Api模块-sign校验】sign校验不合法,[]无内容");
            throw new ApiException(ExceptionEnums.ERROR_SIGN);
        }

        //获取用户预设签名
        Set<Map> set = cacheClient.sMember(CacheConstant.CLIENT_SIGN + submit.getClientId());
        if (null==set||set.size()==0){
            log.info("【Api模块-sign校验】sign校验不合法,用户未绑定签名");
            throw new ApiException(ExceptionEnums.ERROR_SIGN);
        }

        for (Map map : set){
            if (realSign.equals(map.get(CLIENT_SIGN_INFO))){
                log.info("【Api模块-sign校验】sign校验合法");
                submit.setSign(realSign);
                submit.setSignId(Long.parseLong(map.get(SIGN_ID) + ""));
                return;
            }
        }

        log.info("【Api模块-sign校验】sign校验不合法");
        throw new ApiException(ExceptionEnums.ERROR_SIGN);
    }
}
