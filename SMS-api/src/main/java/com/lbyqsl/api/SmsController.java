package com.lbyqsl.api;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.lbyqsl.api.enums.SmsCodeEnums;
import com.lbyqsl.api.filter.CheckFilterContext;
import com.lbyqsl.api.form.SingleSendForm;
import com.lbyqsl.api.utils.R;
import com.lbyqsl.api.vo.ResultVO;
import com.lbyqsl.common.constant.RabbitMQConstants;
import com.lbyqsl.common.model.StandardSubmit;
import com.lbyqsl.common.utills.SnowFlakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private CheckFilterContext checkFilterContext;

    @Autowired
    private SnowFlakeUtil snowFlakeUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

//**请求路径：** https://sms.beaconcloud.com/v1/sms/single_send
//通过网关来进行v1的版本控制
//**请求方式：** POST
    @PostMapping(value = "/single_send",produces = "application/json;charset=utf-8")
    public ResultVO singleSend(@RequestBody @Validated SingleSendForm singleSendForm, BindingResult bindingResult,HttpServletRequest req){

        if (bindingResult.hasErrors()){
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            log.info("[接口模块-单挑短信接口controller]--接口不合法："+defaultMessage);
            return R.error(SmsCodeEnums.PARAMETER_ERROR.getCode(), defaultMessage);
        }

//        获取真实的IP地址
        String ip = this.getRealIP(req);

//        构建StandarSubmit
        StandardSubmit submit=new StandardSubmit();
        submit.setRealIP(ip);
        submit.setApiKey(singleSendForm.getApikey());
        submit.setMobile(singleSendForm.getMobile());
        submit.setText(singleSendForm.getText());
        submit.setReportState(singleSendForm.getState());
        submit.setClientId(singleSendForm.getUid());

        //调用策略模式的校验链
        checkFilterContext.check(submit);

        //基于雪花算法生成唯一id并添加到StandarSubmit中
        submit.setSequenceId(snowFlakeUtil.nextId());

        //发送到MQ，交给策略模块处理
        rabbitTemplate.convertAndSend(RabbitMQConstants.SMS_PRE_SEND,submit,new CorrelationData(submit.getSequenceId().toString()));


        return R.ok();
    }


    //客户端IP地址的请求头信息，多个用','隔开。
    @Value("${headers}")
    private String headers;

    //基于请求头获取信息时，可能获取到的未知信息
    private final String UNKNOW = "unknow";

    //如果是当前请求头获取IP地址，需要截取到第一个','未知
    private final String X_FORWARDED_FOR = "x-forwarded-for";

    /**
     * 获取客户端真实的IP地址
     * @param req
     * @return
     */
    private String getRealIP(HttpServletRequest req) {
        //1. 声明返回的ip地址
        String ip;

        //2. 遍历请求头，并且通过req获取ip地址
        for (String header : headers.split(",")) {
            // 健壮性校验
            if (!StringUtils.isEmpty(header)) {
                // 基于req获取ip地址
                ip = req.getHeader(header);
                // 如果获取到的ip不为null，不为空串，并且不为unknow，就可以返回
                if (!StringUtils.isEmpty(ip) && !UNKNOW.equalsIgnoreCase(ip)) {
                    // 判断请求头是否是x-forwarded-for
                    if (X_FORWARDED_FOR.equalsIgnoreCase(header) && ip.indexOf(",") > 0) {
                        ip = ip.substring(0,ip.indexOf(","));
                    }
                    // 返回IP地址
                    return ip;
                }
            }
        }

        //3. 如果请求头都没有获取到IP地址，直接基于传统的方式获取一个IP
        return req.getRemoteAddr();
    }
}
