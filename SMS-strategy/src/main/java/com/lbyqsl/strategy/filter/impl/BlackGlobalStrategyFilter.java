package com.lbyqsl.strategy.filter.impl;

import com.lbyqsl.common.model.StandardSubmit;
import com.lbyqsl.strategy.filter.StrategyFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "black")
@Slf4j
public class BlackGlobalStrategyFilter implements StrategyFilter {

    @Override
    public void check(StandardSubmit standardSubmit) {
        log.info("【策略模块-黑名单】   校验ing…………");
    }
}
