package com.lbyqsl.test.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbyqsl.test.entity.ClientSign;
import com.lbyqsl.test.mapper.ClientBusinessMapper;
import com.lbyqsl.test.mapper.ClientSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    private ClientSignMapper clientSignMapper;

    @Autowired
    private CacheClient cacheClient;

    @PostMapping("/123/{id}")
    public List<Map> testsadd(@PathVariable("id") Long id){
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

        cacheClient.sadd("client_sign:1",value.toArray(new Map[]{}));
        return value;
    }
}
