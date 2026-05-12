package com.lbyqsl.common.utills;

import com.lbyqsl.common.enums.ExceptionEnums;
import com.lbyqsl.common.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@Component
public class SnowFlakeUtil {


    /**
     * 41个bit位存储时间戳，从0开始计算，最多可以存储69.7年。
     * 那么如果默认使用，从1970年到现在，最多可以用到2039年左右。
     * 按照从2026-5-1号开始计算，存储41个bit位
     */
    private long timeStart = 1777564800000L;

    /**
     * 机器id
     */
    @Value("${snowflake.machineId:0}")
    private long machineId;

    /**
     * 服务id
     */
    @Value("${snowflake.serviceId:0}")
    private long serviceId;

    /**
     * 序列
     */
    private long sequence;

    /**
     * 机器id占用的bit位数
     */
    private long machineIdBits = 5L;

    /**
     * 服务id占用的bit位数
     */
    private long serviceIdBits = 5L;

    /**
     * 序列占用的bit位数
     */
    private long sequenceBits = 12L;

    /**
     * 计算出机器id的最大值
     */
    private long maxMachineId = -1 ^ (-1 << machineIdBits);

    /**
     * 计算出服务id的最大值
     */
    private long maxServiceId = -1 ^ (-1 << serviceIdBits);

    /**
     * 计算出序列号的最大值
     */
    private long maxSequence = -1 ^ (-1 << sequenceBits);

    /**
     * 记录最近一次获取id的时间
     */
    private long lastTimestamp = -1;

    @PostConstruct
    public void init(){
        if(machineId > maxMachineId || serviceId > maxServiceId){
            System.out.println("机器ID或服务ID超过最大范围值！！");
            throw new ApiException(ExceptionEnums.SNOWFLAKE_OUT_OF_RANGE);
        }
    }

    public long genTime(){
        return System.currentTimeMillis();
    }

    public synchronized long nextId(){
        //先获取系统的时间戳
        Long timeStamp=genTime();

        if(timeStamp < lastTimestamp){
            // 说明出现了时间回拨
            System.out.println("当前服务出现时间回拨！！！");
            throw new ApiException(ExceptionEnums.SNOWFLAKE_TIME_BACK);
        }
        //和上一次的时间进行比较,如果小于或等于就增加序列号。
        if (timeStamp==lastTimestamp){
            sequence=(sequence+1)&maxSequence;
            if (sequence==0){  //说明序列号满了,等到下一毫秒再生成
                timeStamp=genTime();
                while (timeStamp<=lastTimestamp){
                    timeStamp=genTime();
                }
            }
        }else{//说明当前时间并没有生成过序列号，给sequence赋一个初始值
            sequence=0;
        }

        //更新时间戳
        lastTimestamp=timeStamp;

        return (timeStamp<<(serviceIdBits+machineIdBits+sequenceBits))|
                (serviceId<<(machineIdBits+sequenceBits))|
                (machineId<<sequenceBits)|
                sequence&Long.MAX_VALUE;
    }


    public static void main(String[] args) {
        SnowFlakeUtil snowFlakeUtil=new SnowFlakeUtil();
        long l = snowFlakeUtil.nextId();
        System.out.println(l);
        System.out.println(Long.toBinaryString(l));
    }
}

