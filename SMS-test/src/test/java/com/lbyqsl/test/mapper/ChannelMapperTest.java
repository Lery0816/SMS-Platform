package com.lbyqsl.test.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbyqsl.test.client.CacheClient;
import com.lbyqsl.test.entity.Channel;
import com.lbyqsl.test.entity.ClientBalance;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ChannelMapperTest {

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    CacheClient cacheClient;

    @Test
    void getBalanceById() throws Exception {
        List<Channel> list = channelMapper.findAll();
        for (Channel channel : list) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.readValue(objectMapper.writeValueAsString(channel), Map.class);
            cacheClient.hmset("channel:" + channel.getId(),map);
        }
    }
}