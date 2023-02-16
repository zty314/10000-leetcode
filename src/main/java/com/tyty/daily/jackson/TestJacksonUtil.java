package com.tyty.daily.jackson;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2021/12/24 13:19
 */
public class TestJacksonUtil {

    public static void main(String[] args) throws URISyntaxException {
        String json = "{\"person_id\":21,\"person_name\":\"小奋斗教程\",\"person_email\":\"1732482792@qq.com\"," +
                "\"person_phone\":\"156983444xx\"}";

        StringBuilder stringBuilder = new StringBuilder();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("aaa", stringBuilder.toString());
        String aaa = JacksonUtils.getJsonConvert().toJsonString(map);
        System.out.println(aaa);

        Person pp = JacksonUtils.getJsonConvert().fromJsonString(json, Person.class);
        System.out.println(pp);

        Map<Object, Person> m = new HashMap<>();
        m.put(1, pp);
        System.out.println(m);
        System.out.println(pp);
        pp.setPersonId(77);
        System.out.println(m);
        System.out.println(pp);
    }
}