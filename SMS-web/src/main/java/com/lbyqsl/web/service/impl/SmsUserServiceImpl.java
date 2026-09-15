package com.lbyqsl.web.service.impl;

import com.lbyqsl.web.entity.SmsUser;
import com.lbyqsl.web.entity.SmsUserExample;
import com.lbyqsl.web.mapper.SmsUserMapper;
import com.lbyqsl.web.service.SmsUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lbyqsl
 * @description
 */
@Service
public class SmsUserServiceImpl implements SmsUserService {

    @Resource
    private SmsUserMapper userMapper;

    @Override
    public SmsUser findByUsername(String username) {
        //1、封装查询条件
        SmsUserExample example = new SmsUserExample();
        SmsUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        //2、基于userMapper查询
        List<SmsUser> list = userMapper.selectByExample(example);
        //3、返回
        return list != null ? list.get(0) : null;
    }
}
