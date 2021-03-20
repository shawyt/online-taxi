package com.shaw.apipassenger.service;

import com.shaw.internalcommon.dto.ResponseResult;

public interface VerificationCodeService {

    ResponseResult send(String phoneNumber);

    ResponseResult verify(String phoneNumber, String code);
}
