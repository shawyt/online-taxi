package com.shaw.apipassenger.service;

import com.shaw.internalcommon.dto.ResponseResult;

public interface AuthService {

    ResponseResult auth(String passengerPhone, String code);
}
