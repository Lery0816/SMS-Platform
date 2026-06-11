package com.lbyqsl.strategy.filter.impl;

import com.lbyqsl.common.constant.CacheConstant;
import com.lbyqsl.common.model.StandardSubmit;
import com.lbyqsl.strategy.client.CacheClient;
import com.lbyqsl.strategy.filter.StrategyFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 携号转网策略！！！！
 * @author zjw
 * @description
 */
@Service(value = "transfer")
@Slf4j
public class TransferStrategyFilter implements StrategyFilter {

    // 代表携号转网了！
    private final Boolean TRANSFER = true;

    @Autowired
    private CacheClient cacheClient;

    @Override
    public void check(StandardSubmit submit) {
        log.info("【策略模块-携号转网策略】   ing…………");
        //1、获取用户手机号
        String mobile = submit.getMobile();

        //2、直接基于Redis查询携号转网信息
        String value = cacheClient.get(CacheConstant.TRANSFER + mobile);

        //3、如果存在携号转网，设置运营商信息
        if(!StringUtils.isEmpty(value)){
            // 代表携号转网了
            submit.setOperatorId(Integer.valueOf(value));
            submit.setIsTransfer(TRANSFER);
            log.info("【策略模块-携号转网策略】   当前手机号携号转网了！");
            return;
        }

        log.info("【策略模块-携号转网策略】  未携号转网！");

    }
}
