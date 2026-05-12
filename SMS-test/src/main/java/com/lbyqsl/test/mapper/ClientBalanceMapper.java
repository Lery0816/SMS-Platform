package com.lbyqsl.test.mapper;

import com.lbyqsl.test.entity.ClientBalance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ClientBalanceMapper {

    @Select("select * from client_balance where client_id=#{client_id}")
    ClientBalance getBalanceById(@Param("client_id") Long client_id);
}
