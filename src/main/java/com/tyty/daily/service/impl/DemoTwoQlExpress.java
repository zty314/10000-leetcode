//package com.kdcom.daily.service.impl;
//
//import com.chinaunicom.ihr.performancemgt.qlexpress.QlExpressHandler;
//import com.chinaunicom.ihr.performancemgt.service.ScoringCriteriaInfoService;
//import com.ql.util.express.ExpressRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//
///**
// * 自定义函数-例子
// * @Author: hwx
// * @Date: 2021/11/18 3:12 下午
// */
//@Component
//public class DemoTwoQlExpress extends QlExpressHandler {
//
//    @Autowired
//    private ScoringCriteriaInfoService scoringCriteriaInfoService;
//
//    public DemoTwoQlExpress(ApplicationContext applicationContext) {
//        super(applicationContext);
//    }
//
//    @Override
//    public void addRunnerFunction(ExpressRunner runner) throws Exception {
//        /**
//         * 添加自定义的对象(例如Spring对象)方法转换为一个表达式计算的函数
//         * 第一个参数: 函数名 自定义 不可重复
//         * 第二个参数: spring对象
//         * 第三个参数: spring对象对应对的方法名
//         * 第四个参数: 方法对应参数类型 可重载
//         * 第五个参数: 如果返回值是boolean类型 且返回false时的输出信息定义
//         */
//        runner.addFunctionOfServiceMethod("读取用户信息",scoringCriteriaInfoService, "selectAll", new Class[] {}, null);
//
//    }
//
//
//}
