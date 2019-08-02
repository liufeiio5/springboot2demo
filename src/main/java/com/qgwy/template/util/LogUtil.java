package com.qgwy.template.util;

import com.qgwy.template.bean.SysLog;
import com.qgwy.template.mapper.SyslogJpaMapper;
import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LogUtil {

    @Autowired
    @Lazy
    private static SyslogJpaMapper syslogJpaMapper;

    public static int insertLog(SysLog sysLog){
        syslogJpaMapper.save(sysLog);
        return 1;
    }
}
