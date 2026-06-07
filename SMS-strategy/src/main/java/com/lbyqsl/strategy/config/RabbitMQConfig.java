package com.lbyqsl.strategy.config;

import com.lbyqsl.common.constant.RabbitMQConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    /**
     * 接口模块发送消息到策略模块的队列
     * @return
     */
    @Bean
    public Queue preSendQueue(){
        return QueueBuilder.durable(RabbitMQConstants.MOBILE_AREA_OPERATOR).build();
    }

    /**
     * 写日志的队列。
     * @return
     */
    @Bean
    public Queue writeLogQueue(){
        return QueueBuilder.durable(RabbitMQConstants.SMS_WRITE_LOG).build();
    }

//    /**
//     * 状态报告的队列。
//     * @return
//     */
//    @Bean
//    public Queue pushReportQueue(){
//        return QueueBuilder.durable(RabbitMQConstants.SMS_PUSH_REPORT).build();
//    }

}
