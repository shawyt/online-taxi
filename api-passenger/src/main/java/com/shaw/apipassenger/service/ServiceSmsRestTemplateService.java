package com.shaw.apipassenger.service;

import com.shaw.internalcommon.dto.ResponseResult;

public interface ServiceSmsRestTemplateService {

    ResponseResult sendSms(String phoneNumber , String code);
}
