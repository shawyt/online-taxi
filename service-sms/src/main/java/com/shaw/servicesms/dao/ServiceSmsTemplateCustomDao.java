package com.shaw.servicesms.dao;

import com.shaw.servicesms.entity.ServiceSmsTemplate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ServiceSmsTemplateCustomDao extends ServiceSmsTemplateDao {

    ServiceSmsTemplate selectByTemplateId(String templateId);
}
