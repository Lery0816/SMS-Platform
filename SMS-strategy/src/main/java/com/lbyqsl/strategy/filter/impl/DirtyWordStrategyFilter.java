package com.lbyqsl.strategy.filter.impl;

import com.lbyqsl.common.constant.CacheConstant;
import com.lbyqsl.common.enums.ExceptionEnums;
import com.lbyqsl.common.exception.StrategyException;
import com.lbyqsl.common.model.StandardSubmit;
import com.lbyqsl.strategy.client.CacheClient;
import com.lbyqsl.strategy.filter.StrategyFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

@Service(value = "dirtyword")
@Slf4j
public class DirtyWordStrategyFilter implements StrategyFilter {

    @Autowired
    CacheClient cacheClient;

    @Override
    public void check(StandardSubmit submit) {
        log.info("【策略模块-敏感词校验】   校验ing…………");
        //1、 获取短信内容
        String text = submit.getText();

        //2、 对短信内容进行分词，并且将分析内容存储到集合中
        Set<String> contents = new HashSet<>();
        StringReader reader = new StringReader(text);
        IKSegmenter ik = new IKSegmenter(reader, false);
        Lexeme lex = null;
        while (true) {
            try {
                if ((lex = ik.next()) == null) break;
            } catch (IOException e) {
                log.info("【策略模块-敏感词校验】   IK分词器在处理短信内容时，出现异常 e = {}", e.getMessage());
            }
            contents.add(lex.getLexemeText());
        }

        //3、 调用Cache缓存模块的交集方法，拿到结果
        Set<Object> dirtyWords = cacheClient.sinterStr(UUID.randomUUID().toString(), CacheConstant.DIRTY_WORD,new ArrayList<>(contents));

        //4、 根据返回的set集合，判断是否包含敏感词
        if (dirtyWords != null && dirtyWords.size() > 0) {
            //5、 如果有敏感词，抛出异常 / 其他操作。。
            log.info("【策略模块-敏感词校验】   短信内容包含敏感词信息， dirtyWords = {}", dirtyWords);
            throw new StrategyException(ExceptionEnums.HAVE_DIRTY_WORD);
        }
    }
}
