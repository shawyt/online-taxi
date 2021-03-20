package com.shaw.apipassenger.service.impl;

import com.shaw.apipassenger.service.ServiceSmsRestTemplateService;
import com.shaw.internalcommon.dto.ResponseResult;
import com.shaw.internalcommon.dto.servicesms.SmsTemplateDto;
import com.shaw.internalcommon.dto.servicesms.request.SmsSendRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ServiceSmsRestTemplateServiceImpl implements ServiceSmsRestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult sendSms(String phoneNumber , String code) {

        String http = "http://";
        String serviceName = "SERVICE-SMS";
        String uri = "/send/sms-template";

        String url = http + serviceName + uri;
        SmsSendRequest smsSendRequest = new SmsSendRequest();
        smsSendRequest.setReceivers(Collections.singletonList(phoneNumber));

        List<SmsTemplateDto> data = new ArrayList<>();
        SmsTemplateDto dto = new SmsTemplateDto();
        dto.setId("SMS_144145499");
        int templateSize = 1;
        HashMap<String, Object> templateMap = new HashMap<String, Object>(templateSize);
        templateMap.put("code", code);
        dto.setTemplateMap(templateMap);
        data.add(dto);

        smsSendRequest.setData(data);

        String s = JSONObject.fromObject(smsSendRequest).toString();


        return restTemplate.postForEntity(url, smsSendRequest, ResponseResult.class).getBody();
    }
}
