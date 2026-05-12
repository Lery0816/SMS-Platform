package com.lbyqsl.test.mapper;

import com.lbyqsl.test.entity.ClientBusiness;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ClientBusinessMapper {

    @Select("select * from client_business where id=#{id}")
    ClientBusiness findById(@Param("id") Long id);
}
