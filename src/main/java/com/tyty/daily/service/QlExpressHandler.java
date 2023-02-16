//package com.kdcom.daily.service;
//
//import cn.hutool.cache.CacheUtil;
//import cn.hutool.cache.impl.FIFOCache;
//import cn.hutool.core.convert.NumberChineseFormatter;
//import cn.hutool.core.map.MapUtil;
//import cn.hutool.core.util.ObjectUtil;
//import cn.hutool.core.util.StrUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.chinaunicom.ihr.kernel.common.exception.ServiceException;
//import com.ql.util.express.ExpressRunner;
//import com.ql.util.express.IExpressContext;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//import java.util.StringJoiner;
//
///**
// * @Author: hwx
// * @Date: 2021/11/18 3:12 下午
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public abstract class QlExpressHandler {
//	/**
//	 * 开启高精度计算模式
//	 */
//	public static ExpressRunner runner = new ExpressRunner(true, false);
//	/**
//	 * spring上下文
//	 */
//	private final ApplicationContext applicationContext;
//
//	/**
//	 * 创建一个先进先出缓存
//	 */
//	FIFOCache<String, String> cache = CacheUtil.newFIFOCache(999);
//
//
//	/**
//	 * 将字符串转换成表达式字符串
//	 *
//	 * @param exp
//	 * @return
//	 */
//	public static String conventExpress(String exp) {
//		List<String> split = StrUtil.splitTrim(exp, ';');
//		StringJoiner stringJoiner = new StringJoiner("\n");
//		for (String str : split) {
//			List<String> strings = StrUtil.splitTrim(str.substring(2), "那么");
//			String format = String.format("如果(%s)那么\n{\n返回 %s;\n}", strings.get(0), strings.get(1));
//			stringJoiner.add(format);
//		}
//		return stringJoiner.toString();
//	}
//
//	/**
//	 * 初始化 操作符和关键字的别名
//	 */
//	static {
//		try {
//			runner.addOperatorWithAlias("如果", "if", null);
//			runner.addOperatorWithAlias("那么", "then", null);
//			runner.addOperatorWithAlias("否则", "else", null);
//			runner.addOperatorWithAlias("且", "and", null);
//			runner.addOperatorWithAlias("或", "or", null);
//			runner.addOperatorWithAlias("加", "+", null);
//			runner.addOperatorWithAlias("减", "-", null);
//			runner.addOperatorWithAlias("乘", "*", null);
//			runner.addOperatorWithAlias("除以", "/", null);
//			runner.addOperatorWithAlias("小于", "<", "$1 小于 $2 不满足期望");
//			runner.addOperatorWithAlias("大于", ">", "$1 大于 $2 不满足期望");
//			runner.addOperatorWithAlias("等于", "==", "$1 等于 $2 不满足期望");
//			runner.addOperatorWithAlias("不等于", "!=", "$1 不等于 $2 不满足期望");
//			runner.addOperatorWithAlias("大于等于", ">=", "$1 大于等于 $2 不满足期望");
//			runner.addOperatorWithAlias("小于等于", "<=", "$1 小于等于 $2 不满足期望");
//
//			runner.addOperatorWithAlias("取模", "mod", null);
//			runner.addOperatorWithAlias("返回", "return", null);
//			runner.addFunctionOfServiceMethod("打印", System.out, "println", new String[]{Object.class.getName()}, null);
//			runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs",
//					new String[]{"double"}, null);
//			runner.addFunctionOfClassMethod("转换中文", QlExpressHandler.class.getName(), "format",
//					new String[]{"double"}, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	/**
//	 * @param exp     程序文本
//	 * @param context 执行上下文
//	 * @param Clazz   添加函数的类
//	 * @return
//	 * @throws Exception
//	 */
//	public Object execute(String exp, IExpressContext<String, Object> context, Class<? extends QlExpressHandler>...
//	Clazz) {
//		try {
//			checkFunction(Clazz);
//			return runner.execute(exp, context, null, true, false, null);
//		} catch (Exception e) {
//		}
//	}
//
//	/**
//	 * @param exp       程序文本
//	 * @param context   执行上下文
//	 * @param errorList 输出的错误信息List
//	 * @param isCache   是否执行缓存
//	 * @return 结果
//	 * @throws Exception
//	 */
//	public Object execute(String exp, IExpressContext<String, Object> context, List<String> errorList, boolean
//	isCache, Class<? extends QlExpressHandler>... Clazz) {
//		try {
//			checkFunction(Clazz);
//			return runner.execute(exp, context, errorList, isCache, false, null);
//		} catch (Exception e) {
//		}
//	}
//
//	/**
//	 * @param exp     程序文本
//	 * @param context 执行上下文
//	 * @param isCache 是否执行缓存
//	 * @param Clazz   添加函数的类
//	 * @return
//	 * @throws Exception
//	 */
//	public Object execute(String exp, IExpressContext<String, Object> context, boolean isCache, Class<? extends
//	QlExpressHandler>... Clazz) {
//		try {
//			checkFunction(Clazz);
//			return runner.execute(exp, context, null, isCache, false, null);
//		} catch (Exception e) {
//		}
//	}
//
//
//	/**
//	 * 防止多次添加重复函数报错
//	 *
//	 * @param Clazz
//	 * @throws Exception
//	 */
//	private void checkFunction(Class<? extends QlExpressHandler>... Clazz) throws Exception {
//
//		if (ObjectUtil.isNotEmpty(Clazz)) {
//			String s = cache.get(Clazz.getClass().getName());
//			if (StrUtil.isEmpty(s) || !StrUtil.equals(s, "1")) {
//				this.addRunnerFunction(runner);
//				cache.put(Clazz.getClass().getName(), "1");
//			}
//		}
//	}
//
//	public static String format(double num) {
//		return NumberChineseFormatter.format(num, false);
//	}
//
//
//	/**
//	 * 如果有自定义新增函数，需要传入自定义类 否则不生效
//	 *
//	 * @param conventExpress 程序文本
//	 * @param data           输入参数
//	 * @param type           返回类型
//	 * @param Clazz          添加函数的类
//	 * @param <T>
//	 * @return 返回当前函数的结果值
//	 */
////	public <T> T result(String conventExpress, Map<String, Object> data, Class<T> type, Class<? extends
// QlExpressHandler>... Clazz) {
////		if (MapUtil.isEmpty(data)) {
////			return null;
////		}
////		IExpressContext expressContext = new QLExpressContext(data,
////				applicationContext);
////		Object execute = execute(conventExpress, expressContext, Clazz);
////		if (ObjectUtil.isEmpty(execute)) {
////			return null;
////		}
////		//String s = JSON.toJSONString(execute);
////		try {
////			//return JSONObject.parseObject(s, type);
////		} catch (Exception e) {
////			log.error("类型转换失败，定义返回类型为：[{}],执行返回结果为[{}]", type.getName(), s);
//////            return null;
////		}
////
////	}
//
//	/**
//	 * 返回当前字段的值
//	 *
//	 * @return
//	 */
//	public String result(String conventExpress, Map<String, Object> data) {
//		return result(conventExpress, data, String.class);
//	}
//
//
//	/**
//	 * 添加执行函数
//	 *
//	 * @param runner
//	 * @throws Exception
//	 */
//	public abstract void addRunnerFunction(ExpressRunner runner) throws Exception;
//}
//
