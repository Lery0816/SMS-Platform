package com.lbyqsl.api.filter;

import com.lbyqsl.common.model.StandardSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@RefreshScope
@Component
public class CheckFilterContext {
    @Autowired
    private Map<String,Checkfilter> checkfilterMap;

    @Value("${filters:apikey}")
    private String filters;

    public void check(StandardSubmit submit){
        String[] filetersList=filters.split(",");
        for (String s : filetersList) {
            checkfilterMap.get(s).Check(submit);
        }
    }

}
