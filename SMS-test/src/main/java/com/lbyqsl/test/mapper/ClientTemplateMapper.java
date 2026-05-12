package com.lbyqsl.test.mapper;

import com.lbyqsl.test.entity.ClientTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientTemplateMapper {

    @Select("select * from client_template where sign_id=#{sign_id}")
    List<ClientTemplate> findBySignId(@Param("sign_id") Long sign_id);
}
