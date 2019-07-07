package com.qgwy.template;

import com.qgwy.template.annotation.DataSource;
import com.qgwy.template.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class McdailyApplicationTests {



    @Test
    public void md5test()
    {
        log.info("text:{},key:{},md5:{}","123456","xint", MD5.md5("123456","xint"));
        log.info("text:{},key:{},md5:{},check:{}","123456","xint",MD5.md5("123456","xint"),MD5.check("123456","xint",MD5.md5("123456","xint")));
    }
}
