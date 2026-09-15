package com.lbyqsl.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.lbyqsl.common.constant.WebMasterConstants;
import com.lbyqsl.common.enums.ExceptionEnums;
import com.lbyqsl.common.utils.R;
import com.lbyqsl.common.vo.ResultVO;
import com.lbyqsl.web.entity.ClientBusiness;
import com.lbyqsl.web.entity.SmsUser;
import com.lbyqsl.web.service.ClientBusinessService;
import com.lbyqsl.web.service.SmsRoleService;
import com.lbyqsl.web.vo.ClientBusinessVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户信息Controller
 * @author lbyqsl
 * @description
 */
@RestController
@Slf4j
public class ClientBusinessController {


    @Autowired
    private SmsRoleService roleService;

    @Autowired
    private ClientBusinessService clientBusinessService;


    @GetMapping("/sys/clientbusiness/all")
    public ResultVO all(){
        //1、拿到当前登录用户的信息
        SmsUser smsUser = (SmsUser) SecurityUtils.getSubject().getPrincipal();
        if(smsUser == null){
            log.info("【获取客户信息】 用户未登录！！");
            return R.error(ExceptionEnums.NOT_LOGIN);
        }
        Integer userId = smsUser.getId();
        //2、查询当前用户的角色信息
        Set<String> roleNameSet = roleService.getRoleName(userId);

        //3、根据角色信息查询数据即可。
        List<ClientBusiness> list = null;
        if(roleNameSet != null && roleNameSet.contains(WebMasterConstants.ROOT)){
            // 查询全部即可
            list = clientBusinessService.findAll();
        }else{
            // 根据用户id查询指定的公司信息
            list = clientBusinessService.findByUserId(userId);
        }
        List<ClientBusinessVO> data = new ArrayList<>();
        for (ClientBusiness clientBusiness : list) {
            ClientBusinessVO vo = new ClientBusinessVO();
            BeanUtils.copyProperties(clientBusiness,vo);
            data.add(vo);
        }
        //4、响应数据
        return R.ok(data);
    }


}
