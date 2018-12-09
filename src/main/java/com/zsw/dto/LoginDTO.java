package com.zsw.dto;

import lombok.Data;

/**
 * @author zhangshiwei
 * @version 1.0
 * @date 2018-12-09
 */
@Data
public class LoginDTO {
    // 用户信息原始数据
    private String rawData;
    // 用户签名，用于验证用户信息是否被篡改过
    private String signature;
    // 用户获取 session_key 的 code
    private String code;
}
