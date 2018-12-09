package com.zsw.controller;

import com.zsw.dto.LoginDTO;
import com.zsw.dto.ResultDTO;
import com.zsw.dto.SessionDTO;
import com.zsw.dto.TokenDTO;
import com.zsw.error.CommonErrorCode;
import com.zsw.error.ErrorCodeException;
import com.zsw.service.WechatAdapterService;
import com.zsw.util.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhangshiwei
 * @version 1.0
 * @date 2018-12-09
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    // Spring 自动注入 wechatAdapter，因 WechatAdapter 类上面有 @Service 注解
    @Autowired
    private WechatAdapterService wechatAdapter;

    // 定义 domain/api/login 访问接口，用于实现登录
    // 使用 LoginDTO 自动解析传递过来的 JSON 数据
    @RequestMapping("/api/login")
    public ResultDTO login(@RequestBody LoginDTO loginDTO) {
        try {
            logger.info("login request : {}", loginDTO);
            // 使用 code 调用微信 API 获取 session_key 和 openid
            SessionDTO sessionDTO = wechatAdapter.jscode2session(loginDTO.getCode());
            logger.info("login get session : {}", sessionDTO);

            // 检验传递过来的使用户信息是否合法
            DigestUtil.checkDigest(loginDTO.getRawData(), sessionDTO.getSessionKey(), loginDTO.getSignature());
            //TODO: 储存 token
            //生成token，用于自定义登录态，这里的存储逻辑比较复杂，放到下一讲
            TokenDTO data = new TokenDTO();
            data.setToken(UUID.randomUUID().toString());
            return ResultDTO.ok(data);
        } catch (ErrorCodeException e) {
            logger.error("login error, info : {}", loginDTO, e.getMessage());
            return ResultDTO.fail(e);
        } catch (Exception e) {
            logger.error("login error, info : {}", loginDTO, e);
            return ResultDTO.fail(CommonErrorCode.UNKOWN_ERROR);
        }
    }
}
