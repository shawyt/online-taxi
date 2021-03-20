package com.shaw.servicesms.service;

import com.shaw.internalcommon.dto.ResponseResult;
import com.shaw.internalcommon.dto.servicesms.request.SmsSendRequest;

public interface SmsService {
	/**
	 * 发送短信
	 * @param request
	 * @return
	 */
	public ResponseResult sendSms(SmsSendRequest request);
}