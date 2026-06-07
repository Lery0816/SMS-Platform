package com.lbyqsl.strategy.util;

import cn.hutool.dfa.WordTree;
import com.lbyqsl.common.constant.CacheConstant;
import com.lbyqsl.strategy.client.CacheClient;

import java.util.List;
import java.util.Set;

public class HutoolDFAUtil {

    private static WordTree wordTree=new WordTree();

    static {
        // 获取Spring容器中的cacheClient
        CacheClient cacheClient = (CacheClient) SpringUtil.getBeanByClass(CacheClient.class);
        // 获取存储在Redis中的全部敏感词
        Set<String> dirtyWords = cacheClient.sMember(CacheConstant.DIRTY_WORD);
        // 调用WordTree的add方法，将dfaMap的敏感词树构建
        wordTree.addWords(dirtyWords);
    }


    public static List<String> getDirtyWord(String text){
        return wordTree.matchAll(text);
    }
}
