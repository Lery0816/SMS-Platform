package com.lbyqsl.test.mapper;

import com.lbyqsl.test.entity.ClientSign;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClientSignMapper {

    @Select("select * from client_sign where client_id=#{client_id}")
    List<ClientSign> findByClientId(@Param("client_id") Long client_id);
}
