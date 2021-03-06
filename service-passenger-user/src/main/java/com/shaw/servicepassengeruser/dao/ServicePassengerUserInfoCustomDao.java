package com.shaw.servicepassengeruser.dao;

import com.shaw.servicepassengeruser.entity.ServicePassengerUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServicePassengerUserInfoCustomDao extends ServicePassengerUserInfoDao{

    /**
     * 根据手机号查询乘客信息
     * @param passengerPhone
     * @return
     */
    ServicePassengerUserInfo selectByPhoneNumber(String passengerPhone);
}