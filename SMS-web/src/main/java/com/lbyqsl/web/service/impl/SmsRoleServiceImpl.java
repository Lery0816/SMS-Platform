package com.lbyqsl.web.service.impl;

import com.lbyqsl.web.mapper.SmsRoleMapper;
import com.lbyqsl.web.service.SmsRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author lbyqsl
 * @description
 */
@Service
public class SmsRoleServiceImpl implements SmsRoleService {

    @Resource
    private SmsRoleMapper roleMapper;

    @Override
    public Set<String> getRoleName(Integer userId) {
        Set<String> roleNameSet = roleMapper.findRoleNameByUserId(userId);
        return roleNameSet;
    }
}
