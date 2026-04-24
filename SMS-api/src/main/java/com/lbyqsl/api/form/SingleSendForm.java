package com.lbyqsl.api.form;

import lombok.Data;

@Data
public class SingleSendForm {

    /** 客户的apikey */
    private String apikey;

    /** 手机号 */
    private String mobile;

    /** 短信内容 */
    private String text;

    /** 客户业务内的uid */
    private String uid;

    /** 0-验证码短信 1-通知类短信 2-营销类短信 */
    private int state;

}
