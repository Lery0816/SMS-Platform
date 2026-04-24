package com.lbyqsl.api;

import com.lbyqsl.api.enums.SmsCodeEnums;
import com.lbyqsl.api.form.SingleSendForm;
import com.lbyqsl.api.utils.R;
import com.lbyqsl.api.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {

//**请求路径：** https://sms.beaconcloud.com/v1/sms/single_send
//通过网关来进行v1的版本控制
//**请求方式：** POST
    @PostMapping(value = "/single_send",produces = "application/json;charset=utf-8")
    public ResultVO singleSend(@RequestBody @Validated SingleSendForm singleSendForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
            log.info("[接口模块-单挑短信接口controller]--接口不合法："+defaultMessage);
            return R.error(SmsCodeEnums.PARAMETER_ERROR.getCode(), defaultMessage);
        }
        return R.ok();
    }
}
