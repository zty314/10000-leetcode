package com.tyty.daily.jackson;

/**
 * XML转换通用接口
 *
 * @author ranran.ye
 * @date 2019年6月11日
 */
public interface XMLConvert {

    /**
     * 对象转XML字符串
     */
    public String toXmlString(Object obj);

    /**
     * XML字符串转对象
     */
    public <T> T fromXmlString(String jsonString, Class<T> clazz);

}
