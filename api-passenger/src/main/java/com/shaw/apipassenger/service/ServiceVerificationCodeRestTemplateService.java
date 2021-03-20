package com.shaw.apipassenger.service;

import com.shaw.internalcommon.dto.ResponseResult;

public interface ServiceVerificationCodeRestTemplateService {

    ResponseResult generatorCode(int identity, String phoneNumber);

    ResponseResult verifyCode(int identity, String phoneNumber , String code);
}
