package com.tyty.daily.collections;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;

import java.util.Collections;
import java.util.List;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/5/10 19:34
 */
public class TestAfterReturn implements InitializingBean {


    public static void main(String[] args) throws InterruptedException {
        TestAfterReturn afterReturn = new TestAfterReturn();
        Runnable run1 = () -> System.out.println(afterReturn.normalLimit());

        Runnable run2 = () -> System.out.println(afterReturn.changeLimit());

        new Thread(run1).start();
        new Thread(run2).start();

        Thread.sleep(1000);
    }

    public synchronized List<Limit> changeLimit() {
        Limit limit = new Limit();
        limit.setId(1);
        limit.setName("a");
        limit.setKey("q");
        limit.setMulti(10);
        limit.setName("小叮当");
        return Collections.singletonList(limit);
    }

    public synchronized List<Limit> normalLimit() {
        Limit limit = new Limit();
        limit.setId(1);
        limit.setName("a");
        limit.setKey("q");
        limit.setMulti(10);
        return Collections.singletonList(limit);
    }

    @Override
    public void afterPropertiesSet() {
    }
}

@Data
class Limit {
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * setting表映射key
     */
    private String key;
    /**
     * 方数
     */
    private Integer multi;
}