package com.qgwy.alpha_web_manager.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.qgwy.alpha_web_manager.annotation.MyLog;
import com.qgwy.alpha_web_manager.bean.SysLog;
import com.qgwy.alpha_web_manager.bean.SysUser;
import com.qgwy.alpha_web_manager.mapper.SyslogJpaMapper;
import com.qgwy.alpha_web_manager.util.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SyslogJpaMapper syslogJpaMapper;

    @Autowired
    private HttpServletRequest request;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.qgwy.alpha_web_manager.annotation.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            String value = myLog.value();
            sysLog.setOperation(value);//保存获取的操作
        }

        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        sysLog.setClazName(className);
        sysLog.setMethodName(methodName);

        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args);
        sysLog.setParams(params);

        sysLog.setLogDate(new Timestamp(new Date().getTime()));
        SysUser userInfo = UserUtils.getUserInfo(request);

        //获取用户名
        sysLog.setUsername(userInfo.getUserId()+"-"+userInfo.getUsername());
        //获取用户ip地址
        //HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //sysLog.setIp(IPUtils.getIpAddr(request));

        //调用service保存SysLog实体类到数据库
        syslogJpaMapper.save(sysLog);
    }

}