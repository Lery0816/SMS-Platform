package com.lbyqsl.test.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbyqsl.test.client.CacheClient;
import com.lbyqsl.test.entity.ClientTemplate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootTest
@RunWith(SpringRunner.class)
class ClientTemplateMapperTest {

    @Autowired
    private ClientTemplateMapper clientTemplateMapper;

    @Autowired
    private CacheClient cacheClient;

    @Test
    void findBySignId() {
        List<ClientTemplate> values1 = clientTemplateMapper.findBySignId(15L);
        List<ClientTemplate> values2 = clientTemplateMapper.findBySignId(24L);
        for (ClientTemplate value : values1) {
            System.out.println(values1);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map> value = values1.stream().map(cs -> {
            try {
                return objectMapper.readValue(objectMapper.writeValueAsString(cs), Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        cacheClient.sadd("client_template:15",value.toArray(new Map[]{}));
    }
}