package com.zftx.mcdaily.controller;


import com.zftx.mcdaily.service.DailyRecordService;
import com.zftx.mcdaily.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private DailyRecordService dailyRecord;

    @RequestMapping("/show")
    @ResponseBody
    public String show() {
        return "hello world ";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "login";
    }

    @RequestMapping("/login")
    public String login() {
        return "jsp/login";
    }





    @RequestMapping("thyme/index")
    public String thymeIndex(){
        return "html/index";
    }


    @RequestMapping("jsp/index")
    public String jspIndex(){
        return "jsp/index2";
    }

    @RequestMapping(value = "/ckeditor/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R addTeaRepository( MultipartRequest mrequest)throws IOException {


        String dir = System.getProperty("user.dir") + "/src/main/resources/static/upload/tea_images/";
        ArrayList<String> urls = new ArrayList<String>();
        List<MultipartFile> files = mrequest.getFiles("file");

        String tImg = "";
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                tImg = "/upload/tea_images/" + file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4, file.getOriginalFilename().length());
                File f = new File(System.getProperty("user.dir") + "/src/main/resources/static/" + tImg);
                urls.add(dir + file.getOriginalFilename());
                //如果文件夹不存在则创建
                if (!f.getParentFile().exists())
                    f.getParentFile().mkdirs();
                //创建文件
                try {
                    file.transferTo(f);

                } catch (IOException e) {
                    e.printStackTrace();
                    return R.error("上传失败");
                }
            } else {
                return R.error("图片不能无");
            }
        }
            return null;
        }

}
