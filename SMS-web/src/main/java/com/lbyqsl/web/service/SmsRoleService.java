package com.lbyqsl.web.service;

import java.util.Set;

/**
 * @author lbyqsl
 * @description
 */
public interface SmsRoleService {
    /**
     * 根据用户id，查询角色名称
     * @param userId
     * @return
     */
    Set<String> getRoleName(Integer userId);
}
