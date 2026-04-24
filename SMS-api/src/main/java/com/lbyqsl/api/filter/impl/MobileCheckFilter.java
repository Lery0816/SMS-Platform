package com.lbyqsl.api.filter.impl;

import com.lbyqsl.api.filter.Checkfilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "mobile")
@Slf4j
public class MobileCheckFilter implements Checkfilter {

    @Override
    public void Check(Object obj) {
        log.info("mobile校验。。。。");
    }
}
