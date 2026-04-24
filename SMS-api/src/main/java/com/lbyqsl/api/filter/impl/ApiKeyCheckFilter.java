package com.lbyqsl.api.filter.impl;

import com.lbyqsl.api.filter.Checkfilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "apikey")
@Slf4j
public class ApiKeyCheckFilter implements Checkfilter {

    @Override
    public void Check(Object obj) {
        log.info("apikey校验。。。。");
    }
}
