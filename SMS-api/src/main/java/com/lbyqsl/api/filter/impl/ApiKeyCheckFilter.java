package com.lbyqsl.api.filter.impl;

import com.lbyqsl.api.client.CacheClient;
import com.lbyqsl.api.filter.Checkfilter;
import com.lbyqsl.common.constant.CacheConstant;
import com.lbyqsl.common.enums.ExceptionEnums;
import com.lbyqsl.common.exception.ApiException;
import com.lbyqsl.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "apikey")
@Slf4j
public class ApiKeyCheckFilter implements Checkfilter {

    @Autowired
    private CacheClient cacheClient;

    @Override
    public void Check(StandardSubmit submit) {
        //基于cacheClient查询客户信息
        Map clientBusiness = cacheClient.hGetAll(CacheConstant.CLIENT_BUSINESS + submit.getApiKey());

        //如果为null直接抛出异常
        if (null==clientBusiness||clientBusiness.size()==0){
            log.info("【Api模块-apikey校验】 非法的apikey={}",submit.getApiKey());
            throw new ApiException(ExceptionEnums.ERROR_APIKEY);
        }

        //正常封装数据
        submit.setClientId(String.valueOf(Long.parseLong(clientBusiness.get("id")+"")));
        log.info("【Api模块-apikey校验】 查询到客户信息 clientBusiness = {}",clientBusiness);
    }
}
