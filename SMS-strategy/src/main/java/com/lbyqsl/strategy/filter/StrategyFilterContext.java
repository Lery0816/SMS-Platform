package com.lbyqsl.strategy.filter;

import com.lbyqsl.common.constant.CacheConstant;
import com.lbyqsl.common.model.StandardSubmit;
import com.lbyqsl.strategy.client.CacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StrategyFilterContext {

    public static final String CLIENT_FILTERS = "clientFilters";

    @Autowired
    private Map<String, StrategyFilter> stringStrategyFilterMap;

    @Autowired
    private CacheClient cacheClient;


    public void strategy(StandardSubmit standardSubmit) {
        String filts = cacheClient.hGet(CacheConstant.CLIENT_BUSINESS + standardSubmit.getApikey(), CLIENT_FILTERS);
        String[] filtsArray;
        if(filts!=null&&(filtsArray=filts.split(",")).length>0){
            for (String s : filtsArray) {
                StrategyFilter strategyFilter = stringStrategyFilterMap.get(s);
                if (strategyFilter!=null){
                    strategyFilter.check(standardSubmit);
                }
            }
        }
    }

}
