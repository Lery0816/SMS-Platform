package com.lbyqsl.api.client;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(Runner.class)
class CacheClientTest {

    @Autowired
    private CacheClient cacheClient;

    @Test
    void hGetAll() {
        Map map = cacheClient.hGetAll("client_business:887559db54d911edba520242ac120002");
        System.out.println(map.toString());
    }
}