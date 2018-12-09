package com.zsw.util;

import com.zsw.error.CommonErrorCode;
import com.zsw.error.ErrorCodeException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangshiwei
 * @version 1.0
 * @date 2018-12-09
 */
public class DigestUtil {
    private static final Logger logger = LoggerFactory.getLogger(DigestUtil.class);
    public static void checkDigest(String rawData, String sessionKey, String signature) {
        logger.info("rawData:{},sessionKey:{},signature:{}", rawData, sessionKey, signature);
        // 调用 apache 的公共包进行 SHA1 加密处理
        String sha1 = DigestUtils.sha1Hex((rawData + sessionKey).getBytes());
        boolean equals = sha1.equals(signature);
        if (!equals) {
            throw new ErrorCodeException(CommonErrorCode.SIGNATURE_ERROR);
        }
    }
}
