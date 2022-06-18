package com.imooc.factory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.imooc.enums.IEnum;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/18 16:47
 */
@Data
public class EnumDeserializer extends JsonDeserializer<IEnum> implements ContextualDeserializer {

    private Class<?> target;

    @Override
    public IEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (!StringUtils.hasText(jsonParser.getText())) {
            return null;
        }
        if (IEnum.class.isAssignableFrom(target)) {
            return IntegerEnumConvertFactory.getEnum((Class<? extends IEnum>) target, Integer.valueOf(jsonParser.getText()));
        }
        return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        Class<?> rawCls = deserializationContext.getContextualType().getRawClass();
        EnumDeserializer enumDeserializer = new EnumDeserializer();
        enumDeserializer.setTarget(rawCls);
        return enumDeserializer;
    }
}
