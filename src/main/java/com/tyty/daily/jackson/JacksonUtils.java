package com.tyty.daily.jackson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * JacksonUtils
 *
 * @author luocanfeng
 * @date 2015年3月5日
 */
public class JacksonUtils {

    private static final JaxbAnnotationModule jaxbAnnotationModule;
    private static final JavaTimeModule javaTimeModule;
    private static final ObjectMapper objectMapper;
    private static final ObjectMapper objectWithoutNullMapper;
    private static final ObjectMapper objectUnderlineHumpMapper;
    //private static final XmlMapper xmlMapper;

    //private static final XMLConvert xmlConvert;
    private static final JsonConvert jsonConvert;
    private static final JsonConvert jsonConvertWithoutNull;
    private static final JsonConvert jsonUnderlineHumpConvert;

    static {
        jaxbAnnotationModule = new JaxbAnnotationModule();
        javaTimeModule = new JavaTimeModule();

        objectWithoutNullMapper = new ObjectMapper();// 转换屏蔽属性为null的场景
        objectWithoutNullMapper.registerModule(jaxbAnnotationModule);
        objectWithoutNullMapper.registerModule(javaTimeModule);
        objectWithoutNullMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectWithoutNullMapper.setSerializationInclusion(Include.NON_NULL);// 属性为NULL不序列化
        objectWithoutNullMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);// Map属性值为null时不序列化
        jsonConvertWithoutNull = new JsonConvertHandler(objectWithoutNullMapper);

        objectMapper = new ObjectMapper();// 标准转换规则
        objectMapper.registerModule(jaxbAnnotationModule);
        objectMapper.registerModule(javaTimeModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConvert = new JsonConvertHandler(objectMapper);

        objectUnderlineHumpMapper = new ObjectMapper();// 标准转换规则
        objectUnderlineHumpMapper.registerModule(jaxbAnnotationModule);
        objectUnderlineHumpMapper.registerModule(javaTimeModule);
        objectUnderlineHumpMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectUnderlineHumpMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        jsonUnderlineHumpConvert = new JsonConvertHandler(objectUnderlineHumpMapper);

        //xmlMapper = new XmlMapper();
        //xmlMapper.registerModule(jaxbAnnotationModule);
        //xmlMapper.registerModule(javaTimeModule);
        //xmlConvert = new XMLConvertHandler(xmlMapper);
    }

    /**
     * XML通用转换方案
     */
    //public static XMLConvert getXMLConvert() {
    //	return xmlConvert;
    //}

    /**
     * JSON默认转换方案（属性为null同步序列化）
     */
    public static JsonConvert getJsonConvert() {
        return jsonConvert;
    }

    /**
     * JSON默认转换方案（属性为null同步序列化,驼峰下划线转换）
     */
    public static JsonConvert getJsonUnderlineHumpConvert() {
        return jsonUnderlineHumpConvert;
    }

    /**
     * JSON定制转换方案（属性为null不进行序列化）
     */
    public static JsonConvert getJsonConvertWithoutNull() {
        return jsonConvertWithoutNull;
    }

}


/**
 * JsonConvertHandler
 *
 * @author ranran.ye
 * @date 2019年6月11日
 */
class JsonConvertHandler implements JsonConvert {

    private ObjectMapper objectMapper;

    public JsonConvertHandler(ObjectMapper objectMapper) {
        super();
        this.objectMapper = objectMapper;
    }

    /**
     * 对象转换为json
     *
     * @param obj
     */
    @Override
    public String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转换为对象
     *
     * @param jsonString
     * @param clazz
     */
    @Override
    public <T> T fromJsonString(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json数组转换为List
     *
     * @param jsonString
     * @param calzz
     */
    @Override
    public <T> List<T> fromJsonList(String jsonString, Class<T> calzz) {
        try {
            JavaType jt = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, calzz);
            return objectMapper.readValue(jsonString, jt);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * json转换为map
     *
     * @param jsonString
     * @param calzz
     */
    @Override
    public <T> Map<String, T> fromJsonmap(String jsonString, Class<T> calzz) {
        try {
            JavaType jvt = objectMapper.getTypeFactory().constructParametricType(HashMap.class, String.class, calzz);
            return objectMapper.readValue(jsonString, jvt);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取json串中某节点对应的值
     *
     * @param jsonString
     * @param node
     */
    @Override
    public String getJsonNodeValue(String jsonString, String node) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString).findValue(node);
            return jsonNode != null ? jsonNode.asText() : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String addJson(String source, String target, String nodeName) {
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(source);
            // 创建子节点
            JsonNode addNode = objectMapper.readTree(target);
            ((ObjectNode) jsonNode).set(nodeName, addNode);
            return objectMapper.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void output(Writer w, Object obj) {
        try {
            objectMapper.writeValue(w, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


/**
 * XMLConvertHandler
 *
 * @author ranran.ye
 * @date 2019年6月11日
 */
//class XMLConvertHandler implements XMLConvert {
//
//	private XmlMapper xmlMapper;
//
//	public XMLConvertHandler(XmlMapper xmlMapper) {
//		super();
//		this.xmlMapper = xmlMapper;
//	}
//
//	/**
//	 * 对象转换为XmlString
//	 */
//	@Override
//	public String toXmlString(Object obj) {
//		try {
//			return xmlMapper.writeValueAsString(obj);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	/**
//	 * String转换为Xml
//	 */
//	@Override
//	public <T> T fromXmlString(String jsonString, Class<T> clazz) {
//		try {
//			return xmlMapper.readValue(jsonString, clazz);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//}
