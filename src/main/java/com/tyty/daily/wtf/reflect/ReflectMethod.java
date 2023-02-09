package com.tyty.daily.wtf.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/19 13:32
 */
//@Component
public class ReflectMethod  {
	private Integer recordStatus;//会议纪要状态
	private Integer signStatus; // 会议签到状态
	private Integer subTitleStatus; // 同声字幕状态
	private static Map<String, Method> methodHashMap = new HashMap<>();
	{
		try {
			methodHashMap.put("subtitle", this.getClass().getMethod("setSubTitleStatus", Integer.class));
			methodHashMap.put("sign", this.getClass().getMethod("setSignStatus", Integer.class));
			methodHashMap.put("record", this.getClass().getMethod("setRecordStatus", Integer.class));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public Integer getSubTitleStatus() {
		return subTitleStatus;
	}

	public void setSubTitleStatus(Integer subTitleStatus) {
		this.subTitleStatus = subTitleStatus;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public Integer getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	public void setStatus(String kisType,Integer status) throws InvocationTargetException, IllegalAccessException {
		Method method = methodHashMap.get(kisType);
		method.invoke(this,status);
	}


}
