package com.shaw.apipassenger.service;

import com.shaw.internalcommon.dto.ResponseResult;

public interface ServicePassengerUserService {

    ResponseResult login(String passengerPhone);
}
