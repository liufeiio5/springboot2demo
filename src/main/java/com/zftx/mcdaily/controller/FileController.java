package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/file")
public class FileController {

//    @RequestMapping("/file")
//    public String file(){ return "redirect:/file.jsp"; };

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public R upload(@RequestParam("tName") String tName, MultipartRequest mrequest) {
        StringBuilder info = new StringBuilder().append(this.getClass().getName()).append("||").append(Thread.currentThread().getStackTrace()[1].getMethodName()).append("&&,##mesage{},data:{}");

        //上传地址
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx"+tName);
        String dir = System.getProperty("user.dir") + "/src/main/resources/public/upload/tea_images";
        ArrayList<String> urls = new ArrayList<String>();
        List<MultipartFile> files = mrequest.getFiles("file");

        for (MultipartFile file : files) {
            File f = new File(dir + file.getOriginalFilename());
            urls.add(dir + file.getOriginalFilename());
            //如果文件夹不存在则创建
            if (!f.getParentFile().exists())
                f.getParentFile().mkdirs();
            //创建文件
            try {
                file.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
                log.info(info.toString(), urls, "上传失败");
            }
        }
        log.info(info.toString(), "上传成功", urls);
        return R.ok().put("urls", urls);
    }
}
