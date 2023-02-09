package com.tyty.daily.volatile_example;

public class VolatileTest {

  public static boolean shareFlag = false;

  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
      System.out.print("开始执行线程1 =>");
      while (!shareFlag){  //shareFlag = false则一直死循环
        //System.out.println("shareFlag=" + shareFlag);
      }
      System.out.print("线程1执行完成 =>");
    }).start();

    Thread.sleep(2000);

    new Thread(() -> {
      System.out.print("开始执行线程2 =>");
      shareFlag = true;
      System.out.print("线程2执行完成 =>");
    }).start();
  }

}

