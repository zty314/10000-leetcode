package com.tyty.daily.wtf;

import java.util.ArrayList;
import java.util.List;

public class ParentClass {
    private int parentX;

    public ParentClass() {
        setX(100);
    }

    public void setX(int x) {
        parentX = x;
    }
}

class ChildClass extends ParentClass {
    private int childX = 1;

    public ChildClass() {
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        childX = x;
        System.out.println("ChildX 被赋值为 " + x);
    }

    public void printX() {
        System.out.println("ChildX = " + childX);
    }

}

class TryInitMain {
    public static void main(String[] args) {
        //ChildClass cc = new ChildClass();
        //cc.printX();
        List l1 = new ArrayList();
        String s1 = new String("abc").intern();
        l1.add(s1);
        List<Integer> l2 = l1;
        System.out.println(l2.get(0).intValue());
    }
}