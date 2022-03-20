package com.zdsy.drivingschoolreservationsystem.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WxSession implements Serializable {
    private String openid;// 用户唯一标识
    @JsonProperty("session_key")
    private String sessionKey;// 会话密钥
    private String unionid;// 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
    private Integer errcode;// 错误码
    private String errmsg;// 错误信息
}
