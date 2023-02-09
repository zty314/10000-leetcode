//package com.kdcom.daily.service.impl;
//
//import com.chinaunicom.ihr.performancemgt.qlexpress.QlExpressHandler;
//import com.ql.util.express.ExpressRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//
///**
// * 自定义函数-例子
// *
// * @Author: hwx
// * @Date: 2021/11/18 3:12 下午
// */
//@Component
//public class DemoQlExpress extends QlExpressHandler {
//
//
//    public DemoQlExpress(ApplicationContext applicationContext) {
//        super(applicationContext);
//    }
//
//    @Override
//    public void addRunnerFunction(ExpressRunner runner) throws Exception {
//        runner.addMacro("总工资", "调薪额度+月薪额度+年终奖金");
//    }
//
//
//}
