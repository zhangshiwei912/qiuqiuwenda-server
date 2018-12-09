package com.zsw.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zhangshiwei
 * @version 1.0
 * @date 2018-12-09
 */
@Data
public class SessionDTO {

    private String openid;

    @JSONField(name = "session_key")
    private String sessionKey;
}
