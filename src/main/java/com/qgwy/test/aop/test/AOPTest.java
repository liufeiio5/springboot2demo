package com.qgwy.test.aop.test;

import com.qgwy.test.aop.service.AService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest{
    public static void main(String[] args){
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        AService aService = (AService)factory.getBean("aService");
        aService.cool();
        aService.cool("泉浴五江");
    }
}