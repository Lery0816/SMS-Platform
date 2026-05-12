package com.lbyqsl.test.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbyqsl.test.client.CacheClient;
import com.lbyqsl.test.entity.ClientBalance;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ClientBalanceMapperTest {

    @Autowired
    ClientBalanceMapper clientBalanceMapper;

    @Autowired
    CacheClient cacheClient;

    @Test
    void getBalanceById() throws Exception {
        ClientBalance clientBalance = clientBalanceMapper.getBalanceById(1L);
        System.out.println(clientBalance);
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(objectMapper.writeValueAsString(clientBalance), Map.class);
        cacheClient.hmset("client_balance:1",map);
    }
}