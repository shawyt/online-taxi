package com.shaw.servicepassengeruser.service;

import com.shaw.internalcommon.dto.ResponseResult;

public interface PassengerUserService {

    ResponseResult login(String passengerPhone);

    ResponseResult logout(String token);
}
