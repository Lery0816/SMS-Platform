package com.lbyqsl.test.mapper;

import com.lbyqsl.test.client.CacheClient;
import com.lbyqsl.test.entity.MobileBlack;
import com.lbyqsl.test.mapper.MobileBlackMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class MobileBlackMapperTest {

    @Autowired
    private CacheClient cacheClient;

    @Autowired
    private MobileBlackMapper mobileBlackMapper;

    @Test
    void findAll() {
        List<MobileBlack> all = mobileBlackMapper.findAll();
        for (MobileBlack mobileBlack : all) {
            if (mobileBlack.getClientId()==0){
                cacheClient.set("black:"+mobileBlack.getBlackNumber(),"1");
            }else{
                cacheClient.set("black:"+mobileBlack.getClientId()+":"+mobileBlack.getBlackNumber(),"1");
            }
        }
    }
}