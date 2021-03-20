package com.shaw.internalcommon.dto.servicesms.request;

import com.shaw.internalcommon.dto.servicesms.SmsTemplateDto;
import lombok.Data;

import java.util.List;

/**
 * @author yueyi2019
 */
@Data
public class SmsSendRequest {

	private List<String> receivers;
	private List<SmsTemplateDto> data;

	@Override
	public String toString() {
		return "SmsSendRequest [reveivers=" + receivers + ", data=" + data + "]";
	}

}