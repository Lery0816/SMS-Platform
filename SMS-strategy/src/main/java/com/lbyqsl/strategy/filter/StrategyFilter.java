package com.lbyqsl.strategy.filter;

import com.lbyqsl.common.model.StandardSubmit;

public interface StrategyFilter {
    void check(StandardSubmit standardSubmit);
}
