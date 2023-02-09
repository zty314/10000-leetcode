package com.tyty.daily;

import com.tyty.daily.wtf.reflect.ReflectMethod;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/11/19 13:33
 */
@SpringBootTest
public class TestReflectMethod {

	//@Autowired
	//ReflectMethod reflectMethod;

	@Test
	public void testMethod() throws InvocationTargetException, IllegalAccessException {
		ReflectMethod reflectMethod = new ReflectMethod();
		reflectMethod.setStatus("sign",1);

		System.out.println(reflectMethod.getSignStatus());
	}
}
