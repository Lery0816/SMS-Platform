package com.lbyqsl.web.service;

import com.lbyqsl.web.entity.ClientBusiness;

import java.util.List;

/**
 * @author lbyqsl
 * @description
 */
public interface ClientBusinessService {
    /**
     * 查询全部客户信息
     * @return
     */
    List<ClientBusiness> findAll();

    /**
     * 根据用户id查询客户信息
     * @param userId
     * @return
     */
    List<ClientBusiness> findByUserId(Integer userId);
}
