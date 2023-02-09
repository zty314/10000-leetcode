package com.tyty.daily.wtf;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/10/25 15:36
 */
public class ObjectKeyMap {

	@Data
	static
	class Key {
		String name;
	}

	public static void main(String[] args) {
		ObjectKeyMap.Key key = new ObjectKeyMap.Key();
		key.setName("1");
		Map<Key, Integer> map = new HashMap<>();
		map.put(key, new Integer(1));
		key.setName("2");
		map.put(key, new Integer(2));
		map.forEach((k, v) -> {
			System.out.println("map : " + k + " : " + v);
		});
	}
}
