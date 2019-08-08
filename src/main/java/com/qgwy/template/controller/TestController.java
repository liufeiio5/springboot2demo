package com.qgwy.template.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qgwy.template.bean.DailyRecord;
import com.qgwy.template.bean.SysLog;
import com.qgwy.template.mapper.DailyRecordMapper;
import com.qgwy.template.mapper.SyslogJpaMapper;
import com.qgwy.template.util.DynamicDataSource;
import com.qgwy.template.util.FastJsonUtil;
import com.qgwy.template.util.LogUtil;
import com.qgwy.template.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@Api(value = "测试类",tags = {"1-测试"})
public class TestController {

   /* @Autowired
    @Lazy
    private DailyRecordService dailyRecord;*/
   @Autowired
   private SyslogJpaMapper jpaMapper;

   @Value("${apollo-name:haha}")
   private String apolloName;

    @Autowired
    private DynamicDataSource dataSource;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DailyRecordMapper dailyRecordMapper;

    @GetMapping("/show")
    @ResponseBody
    @ApiOperation(value = "1.2-日报查询",notes = "查询选定时候段内某人的所有日报详情")
    @ApiResponses(@ApiResponse(code = 200,message = "请求成功"))
    public R show() {

        System.out.println(dataSource.getClass());
        try {
            final Connection connection = dataSource.getConnection();
            System.out.println(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> daily = dailyRecordMapper.getDaily(92, "20190601", "20190705");
        log.info("查询日报数据如下:{}",daily);
        return R.ok().put("data",daily);
    }
    @GetMapping("/show/http")
    @ResponseBody
    @ApiOperation(value = "1.2-以http方式做日报查询",notes = "查询选定时候段内某人的所有日报详情")
    @ApiResponses(@ApiResponse(code = 200,message = "请求成功"))
    public R showHttp() {

        String apiURL = "http://192.168.100.56:49000/show";
        //String apiURL = "http://192.168.100.56:39000/show2";
        //DailyRecord[] result = restTemplate.getForObject(apiURL, DailyRecord[].class);
        String result = restTemplate.getForObject(apiURL, String.class);
        HashMap<String, Object> resultMap = FastJsonUtil.json2Map(result);
        System.out.println(resultMap.get("data"));
        JSONArray jsonArray= (JSONArray) resultMap.get("data");
        System.out.println(jsonArray.get(1));
        List<DailyRecord> list=JSONObject.parseArray(jsonArray.toJSONString(),DailyRecord.class);
        System.out.println(list);
        //System.out.println(data);
        //ArrayList<HashMap<String, Object>> daily = dailyRecordMapper.getDaily(92, "20190601", "20190705");
        log.info("查询日报数据如下:{}",result);
        return R.ok().put("data",resultMap.get("data"));
    }

    @GetMapping("/index")
    @ApiOperation("测试默认的返回页面")
    public String toIndex() {
        return "login";
    }

    @GetMapping("/login")
    @ApiOperation("测试登陆页面")
    public String login() {
        return "jsp/login";
    }


    @GetMapping("thyme/index")
    @ApiOperation("测试thyme的返回页面")
    public String thymeIndex(){
        return "html/index";
    }

    //@RequestMapping(value = "show2",produces = "application/json; charset=utf-8")
    @GetMapping(value = "show2")
    @ResponseBody
    @ApiOperation("测试文本方式返回")
    public String show2()
    {
        log.info("hello {},welcome!","fei哥aaa");
        System.out.println("我试下有不有");
        return "好像没什么反应并不是很对";
    }

    @GetMapping(value = "show3")
    @ResponseBody
    @ApiOperation("测试自定义对象返回")
    public R json()
    {
        return R.ok().put("data","有木有乱码");
    }


    @GetMapping("jsp/index")
    @ApiOperation("测试jsp的返回页面")
    public String jspIndex(){
        System.out.println("好像OK，是不是");
        //System.out.println(111121212);
        return "jsp/index2";
    }

    @GetMapping("/showlog")
    @ResponseBody
    public R testLog(){
        //jpaMapper.save(new SysLog().setClazName("dsfdsf").setMethodName("m-54654").setLogLevel("info").setMessage("插入测试").setLogDate(new Timestamp(new Date().getTime())));
        LogUtil.insertLog(new SysLog().setClazName("dsfdsf").setMethodName("m-54654").setLogLevel("info").setMessage("插入测试").setLogDate(new Timestamp(new Date().getTime())));
        return R.ok().put("data","success");
    }

    /*@GetMapping("/encPass")
    @ResponseBody
    public R testPass(){
        //jpaMapper.save(new SysLog().setClazName("dsfdsf").setMethodName("m-54654").setLogLevel("info").setMessage("插入测试").setLogDate(new Timestamp(new Date().getTime())));

            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            //加密所需的salt(盐)
            textEncryptor.setPassword("G0CvDz7oJn6");
            //要加密的数据（数据库的用户名或密码）
            String username = textEncryptor.encrypt("root");
            String password = textEncryptor.encrypt("123456");
            System.out.println("username:"+username);
            System.out.println("password:"+password);

        //LogUtil.insertLog(new SysLog().setClazName("dsfdsf").setMethodName("m-54654").setLogLevel("info").setMessage("插入测试").setLogDate(new Timestamp(new Date().getTime())));
        return R.ok().put("data","success");
    }*/

    @GetMapping("/hi/apollo")
    @ResponseBody
    public String hiApollo(){
        return apolloName;
    }
}
