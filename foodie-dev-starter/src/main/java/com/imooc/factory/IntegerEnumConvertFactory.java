package com.imooc.factory;

import com.imooc.enums.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2022/6/18 16:28
 */
@Component
public class IntegerEnumConvertFactory implements ConverterFactory<Integer, IEnum> {
    @Override
    public <T extends IEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new Converter<Integer, T>() {
            @Override
            public T convert(Integer source) {
                return IntegerEnumConvertFactory.getEnum(targetType, source);
            }
        };
    }

    public static <T extends IEnum> T getEnum(Class<T> type, Integer code) {
        T[] enumConstants = type.getEnumConstants();
        for (T enumConstant : enumConstants) {
            if (enumConstant.getCode() == code) {
                return enumConstant;
            }
        }
        return null;
    }
}
