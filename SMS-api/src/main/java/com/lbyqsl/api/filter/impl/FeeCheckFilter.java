package com.lbyqsl.api.filter.impl;

import com.lbyqsl.api.filter.Checkfilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "fee")
@Slf4j
public class FeeCheckFilter implements Checkfilter {

    @Override
    public void Check(Object obj) {
        log.info("fee校验。。。。");
    }
}
