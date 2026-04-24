package com.lbyqsl.api.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SingleSendForm {

    @NotBlank(message = "apikey不允许为空")
    /** 客户的apikey */
    private String apikey;

    @NotBlank(message = "手机号不允许为空")
    /** 手机号 */
    private String mobile;

    @NotBlank(message = "短信内容不允许为空")
    /** 短信内容 */
    private String text;

    /** 客户业务内的uid */
    private String uid;

    @Range(min=0,max=2,message = "短信类型只能是0~2的整数")
    @NotNull(message = "短信类型不允许为空")
    /** 0-验证码短信 1-通知类短信 2-营销类短信 */
    private int state;

}
