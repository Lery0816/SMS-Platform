package com.lbyqsl.test.mapper;

import com.lbyqsl.test.client.CacheClient;
import com.lbyqsl.test.entity.ClientTemplate;
import com.lbyqsl.test.entity.MobileArea;
import com.lbyqsl.test.mapper.MobileAreaMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MobileAreaMapperTest {

    @Autowired
    MobileAreaMapper mobileAreaMapper;

    @Autowired
    CacheClient cacheClient;


    @Test
    void findBySignId() {
        List<MobileArea> allMobile = mobileAreaMapper.getAllMobile();
        Map<String,String> map=new HashMap<>(allMobile.size());
        for (MobileArea mobileArea : allMobile) {
            map.put("phase:"+mobileArea.getMobileNumber(),mobileArea.getMobileArea()+","+mobileArea.getMobileType());
        }
        cacheClient.pipelineString(map);
    }
}