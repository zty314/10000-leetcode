package com.tyty.daily.lua;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/4/13 10:05
 */
public class MccRedisScript {

    public static void main(String[] args) {
        RedisScript<String> script = getScript("lua/hello.lua", String.class);
        System.out.println(script.getScriptAsString());
    }

    public static <T> RedisScript<T> getScript(String scriptLocation, Class<T> resultType) {
        DefaultRedisScript<T> redisScript = new DefaultRedisScript<T>();
        redisScript.setLocation(new ClassPathResource(scriptLocation));
        redisScript.setResultType(resultType);
        return redisScript;
    }

    public static Comparable ccc = o -> {
        System.out.println("static var");
        return 0;
    };

    static{
        ccc = o -> {
            System.out.println("static block");
            return 0;
        };
    }
}