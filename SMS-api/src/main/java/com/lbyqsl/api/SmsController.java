package com.lbyqsl.api;

import com.lbyqsl.api.form.SingleSendForm;
import com.lbyqsl.api.utils.R;
import com.lbyqsl.api.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

//**请求路径：** https://sms.beaconcloud.com/v1/sms/single_send
//通过网关来进行v1的版本控制
//**请求方式：** POST
    @PostMapping(value = "/single_send",produces = "application/json;charset=utf-8")
    public ResultVO singleSend(@RequestBody SingleSendForm singleSendForm){
        return R.ok();
    }
}
