package com.lbyqsl.strategy.filter.impl;

import com.lbyqsl.common.enums.ExceptionEnums;
import com.lbyqsl.common.exception.StrategyException;
import com.lbyqsl.common.model.StandardSubmit;
import com.lbyqsl.strategy.filter.StrategyFilter;
import com.lbyqsl.strategy.util.DFAUtil;
import com.lbyqsl.strategy.util.ErrorSendMsgUtil;
import com.lbyqsl.strategy.util.HutoolDFAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 敏感词校验
 */
@Service(value = "HutooldfaDirtyWord")
@Slf4j
public class DirtyWordHutoolDFAStrategyFilter implements StrategyFilter {

    @Autowired
    private ErrorSendMsgUtil errorSendMsgUtil;

    @Override
    public void check(StandardSubmit submit) {
        log.info("【策略模块-敏感词校验】   校验ing…………");
        //1、 获取短信内容
        String text = submit.getText();

        //2、 调用DFA查看敏感词
        List<String> dirtyWords = HutoolDFAUtil.getDirtyWord(text);

        //4、 根据返回的set集合，判断是否包含敏感词
        if (dirtyWords != null && dirtyWords.size() > 0) {
            //5、 如果有敏感词，抛出异常 / 其他操作。。
            log.info("【策略模块-敏感词校验】   短信内容包含敏感词信息， dirtyWords = {}", dirtyWords);
            // ================================发送写日志================================
            submit.setErrorMsg(ExceptionEnums.HAVE_DIRTY_WORD.getMsg() + "dirtyWords = " + dirtyWords);
            errorSendMsgUtil.sendWriteLog(submit);
            // ================================发送状态报告的消息前，需要将report对象数据封装================================
            errorSendMsgUtil.sendPushReport(submit);
            // // ================================抛出异常================================
            throw new StrategyException(ExceptionEnums.HAVE_DIRTY_WORD);

        }
    }
}

