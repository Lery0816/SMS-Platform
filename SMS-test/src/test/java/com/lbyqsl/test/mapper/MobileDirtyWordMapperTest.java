package com.lbyqsl.test.mapper;

import com.lbyqsl.test.client.CacheClient;
import com.lbyqsl.test.mapper.MobileDirtyWordMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MobileDirtyWordMapperTest {

    @Autowired
    CacheClient cacheClient;

    @Autowired
    MobileDirtyWordMapper dirtyWordMapper;

    @Test
    void findDirtyWord() {
        List<String> dirtyWords = dirtyWordMapper.findDirtyWord();
        cacheClient.saddstr("dirty_word",dirtyWords);
    }
}