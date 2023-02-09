package com.tyty.daily.jackson;

import java.io.Writer;
import java.util.List;
import java.util.Map;

public interface JsonConvert {

	/**
	 * 对象转JSON字符串
	 */
	public String toJsonString(Object obj);

	/**
	 * JSON字符串转对象
	 */
	public <T> T fromJsonString(String jsonString, Class<T> clazz);

	/**
	 * JSON数组字符串转List集合对象
	 */
	public <T> List<T> fromJsonList(String jsonString, Class<T> calzz);

	/**
	 * Json对象字符串转Map集合对象
	 */
	public <T> Map<String, T> fromJsonmap(String jsonString, Class<T> calzz);

	/**
	 * 获取Json串中节点的值
	 *
	 * @param jsonString
	 *            json字符串
	 * @param node
	 *            属性名称
	 *
	 * @return 属性值
	 */
	public String getJsonNodeValue(String jsonString, String node);

	/**
	 * JSON串合并到子属性
	 *
	 * @param source
	 *            被合入的json字符串
	 * @param target
	 *            待合入的json字符串
	 * @param nodeName
	 *            合入后的子属性名称
	 *
	 * @return 合入后的json字符串
	 */
	public String addJson(String source, String target, String nodeName);

	/**
	 * 将对象转换为JSON串后输出到Wirter
	 *
	 * <pre>
	 * 使用场景：后端不需要使用到该JSON字符串，可以通过response直接输出给前端使用
	 * </pre>
	 */
	public void output(Writer w, Object obj);

}