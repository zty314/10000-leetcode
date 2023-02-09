package com.tyty.daily.optional;

import lombok.Data;

import java.util.Optional;

/**
 * @version: v1.0
 * @author: zhangty
 * @date: 2022/5/12 10:07
 */
public class TestMap {

    public static void main(String[] args) {
        /*String d = new A().getB().getC().getD();//NPE
        System.out.println(d);*/
        A a = new A();
        String fff = Optional.ofNullable(a).map(A::getB).map(B::getC).map(C::getD).orElse("FFF");
        System.out.println(fff);
    }
}

@Data
class A {
    B b = new B();
}

@Data
class B {
    C c = new C();
}

@Data
class C {
    String d = "DDD";
}