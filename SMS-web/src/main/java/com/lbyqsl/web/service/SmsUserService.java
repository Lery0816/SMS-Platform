package com.lbyqsl.web.service;

import com.lbyqsl.web.entity.SmsUser;

/**
 * 用户信息的Service
 * @author lbyqsl
 * @description
 */
public interface SmsUserService {


    /**
     * 根据用户名查询用户信息
     * @param username  用户名
     * @return
     */
    SmsUser findByUsername(String username);

}
