package com.shaw.serviceverificationcode.service.impl;

import com.shaw.internalcommon.constant.CommonStatusEnum;
import com.shaw.internalcommon.constant.IdentityConstant;
import com.shaw.internalcommon.constant.RedisKeyPrefixConstant;
import com.shaw.internalcommon.dto.ResponseResult;
import com.shaw.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import com.shaw.serviceverificationcode.service.VerifyCodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: xiaoyantao
 * @Date: 2020/12/18  20:47
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber) {
        // 校验 发送时限，三挡验证，不能无限制发短信
        // checkSendCodeTimeLimit(phoneNumber);

        // 0.9*9=8.1+1 9,去掉首位为0的情况。 0.11225478552211(0.0-<1)
        String code = String.valueOf((int)((Math.random() * 9 + 1) * Math.pow(10,5)));

        /**
         * 有人用这种写法。生成6位code，错误用法，虽然大部分情况下结果正确，但不能这么用，偶尔位数不够？
         */
        // String code = String.valueOf(new Random().nextInt(1000000));

        // 生成redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;
        // 存redis，2分钟过期
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);

        // Boolean aBoolean = codeRedis.setIfAbsent(code);
        // if (aBoolean){
        //    codeRedis.expire(2,TimeUnit.MINUTES);
        // }
        codeRedis.set(code,2, TimeUnit.MINUTES);
        // codeRedis.expire(2,TimeUnit.MINUTES);

        //返回
        VerifyCodeResponse result = new VerifyCodeResponse();
        result.setCode(code);
        return ResponseResult.success(result);
    }

    @Override
    public ResponseResult verify(int identity,String phoneNumber,String code) {
        //三档验证

        //生成redis key
        String keyPre = generateKeyPreByIdentity(identity);
        String key = keyPre + phoneNumber;
        BoundValueOperations<String, String> codeRedis = redisTemplate.boundValueOps(key);
        String redisCode = codeRedis.get();

        if(StringUtils.isNotBlank(code) && StringUtils.isNotBlank(redisCode)
                && code.trim().equals(redisCode.trim())) {
            return ResponseResult.success("");
        }else {
            return ResponseResult.fail(CommonStatusEnum.VERIFY_CODE_ERROR.getCode(), CommonStatusEnum.VERIFY_CODE_ERROR.getValue());
        }
    }

    /**
     * 根据身份类型生成对应的缓存key
     * @param identity
     * @return
     */
    private String generateKeyPreByIdentity(int identity){
        String keyPre = "";
        if (identity == IdentityConstant.PASSENGER){
            keyPre = RedisKeyPrefixConstant.PASSENGER_LOGIN_CODE_KEY_PRE;
        }else if (identity == IdentityConstant.DRIVER){
            keyPre = RedisKeyPrefixConstant.DRIVER_LOGIN_CODE_KEY_PRE;
        }
        return keyPre;
    }






}
