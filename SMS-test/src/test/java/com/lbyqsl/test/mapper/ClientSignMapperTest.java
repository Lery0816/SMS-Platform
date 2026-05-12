package com.lbyqsl.test.mapper;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbyqsl.test.client.CacheClient;
import com.lbyqsl.test.entity.ClientSign;
import com.lbyqsl.test.mapper.ClientSignMapper;
import io.prometheus.client.Collector;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class ClientSignMapperTest {

    @Autowired
    private ClientSignMapper clientSignMapper;

    @Autowired
    private CacheClient cacheClient;

    @Test
    void findByClientId() {
        Long id =1L;
        List<ClientSign> clients = clientSignMapper.findByClientId(id);
        for (ClientSign client : clients) {
            System.out.println(client);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map> value = clients.stream().map(cs -> {
            try {
                return objectMapper.readValue(objectMapper.writeValueAsString(cs), Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        cacheClient. sadd("client_sign:"+id,value.toArray(new Map[]{}));
    }
}