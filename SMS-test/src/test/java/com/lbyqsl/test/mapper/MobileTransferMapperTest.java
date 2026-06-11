package com.lbyqsl.test.mapper;

import com.lbyqsl.test.client.CacheClient;
import com.lbyqsl.test.entity.MobileTransfer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MobileTransferMapperTest {

    @Autowired
    private CacheClient cacheClient;

    @Autowired
    private MobileTransferMapper mobileTransferMapper;

    @Test
    void findAll() {
        List<MobileTransfer> all = mobileTransferMapper.findAll();
        for (MobileTransfer mobileTransfer : all) {
            cacheClient.set("transfer:"+mobileTransfer.getTransferNumber(),mobileTransfer.getNowIsp());
        }
    }
}