package com.lbyqsl.api.filter.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.lbyqsl.api.client.CacheClient;
import com.lbyqsl.api.filter.Checkfilter;
import com.lbyqsl.common.constant.CacheConstant;
import com.lbyqsl.common.enums.ExceptionEnums;
import com.lbyqsl.common.exception.ApiException;
import com.lbyqsl.common.model.StandardSubmit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ip")
@Slf4j
public class IPCheckFilter implements Checkfilter {
    @Autowired
    private CacheClient cacheClient;

    private final String IP_ADDRESS = "ipAddress";

    @Override
    public void Check(StandardSubmit submit) {
        //从cache模块中查询用户的ip白名单
        List<String> ip = (List<String>) cacheClient.hGet(CacheConstant.CLIENT_BUSINESS + submit.getApikey(), IP_ADDRESS);
        submit.setIp(ip);

        //如果白名单为null，说明用户没有设置白名单，直接放行，或者与白名单ip相符
        if (null==ip||ip.contains(submit.getRealIP())){
            log.info("【Api模块-ip校验】ip{}校验合法",ip);
            return;
        }

        //白名单不为null且和白名单不相符，抛出异常
        log.info("【Api模块-ip校验】 非法的ip={}",submit.getRealIP());
        throw new ApiException(ExceptionEnums.IP_NOT_WHITE);
    }
}
