package com.imooc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.imooc.enums.IEnum;
import com.imooc.factory.EnumDeserializer;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/18 16:58
 */
@Component
public class JacksonConfig implements SmartInitializingSingleton {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void afterSingletonsInstantiated() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(IEnum.class, new EnumDeserializer());
        objectMapper.registerModule(simpleModule);
    }
}
