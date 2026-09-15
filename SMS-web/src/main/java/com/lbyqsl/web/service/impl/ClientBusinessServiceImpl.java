package com.lbyqsl.web.service.impl;

import com.lbyqsl.web.entity.ClientBusiness;
import com.lbyqsl.web.entity.ClientBusinessExample;
import com.lbyqsl.web.mapper.ClientBusinessMapper;
import com.lbyqsl.web.service.ClientBusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lbyqsl
 * @description
 */
@Service
public class ClientBusinessServiceImpl implements ClientBusinessService {

    @Resource
    private ClientBusinessMapper clientBusinessMapper;

    @Override
    public List<ClientBusiness> findAll() {
        List<ClientBusiness> list = clientBusinessMapper.selectByExample(null);
        return list;
    }

    @Override
    public List<ClientBusiness> findByUserId(Integer userId) {
        ClientBusinessExample example = new ClientBusinessExample();
        example.createCriteria().andExtend1EqualTo(userId + "");
        List<ClientBusiness> list = clientBusinessMapper.selectByExample(example);
        return list;
    }
}
