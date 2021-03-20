package com.shaw.serviceverificationcode.service;

import com.shaw.internalcommon.dto.ResponseResult;
import com.shaw.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;

/**
 * @Author: xiaoyantao
 * @Date: 2020/12/18  20:46
 */
public interface VerifyCodeService {

    /**
     * 根据身份和手机号生成验证码
     * @param identity 身份
     * @param phoneNumber 手机号
     * @return
     */
    ResponseResult<VerifyCodeResponse> generate(int identity , String phoneNumber);


    /**
     * 校验身份，手机号，验证码的合法性
     * @param identity 身份
     * @param phoneNumber 手机号
     * @param code 验证码
     * @return
     */
    ResponseResult verify(int identity,String phoneNumber,String code);


}
