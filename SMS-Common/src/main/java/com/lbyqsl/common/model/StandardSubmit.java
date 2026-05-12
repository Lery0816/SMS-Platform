package com.lbyqsl.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardSubmit implements Serializable {
//    当前短信的唯一标识
    private Long sequenceId;

//    客户id
    private String clientId;

//    客户端ip
    private String ip;

//    目标手机号
    private String mobile;

//    短信内容的签名
    private String sign;

//    短信内容
    private String text;

//    短信发送的时间
    private LocalDateTime sendTime;

//    当前短信的费用
    private Long fee;

//    客户的手机运营商
    private Integer operatorId;

//    客户的手机区号
    private Integer areaCode;

//    用户手机的归属地
    private String area;

//    通道下发的源号码
    private String srcNumber;

//    通道的id信息
    private Long channelId;

//    短信的发生状态：0-等待  1-成功  2-失败
    private int reportState;

//    获取到的客户端真实的Ip
    private String realIP;

//    请求携带的apikey
    private String apiKey;

    /**
     *  0-验证码短信 1-通知类短信 2-营销类短信
     */
    private int state;

    /**
     * 签名的id
     */
    private Long signId;

    /**
     * 是否携号转网，  isTransfer = true，代表做了携号转网的判断并且做了操作
     */
    private Boolean isTransfer = false;

    /**
     *  针对1小时限流规则存储的系统时间毫秒值
     */
    private Long oneHourLimitMilli;
}
