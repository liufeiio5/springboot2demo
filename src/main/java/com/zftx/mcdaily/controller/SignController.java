package com.zftx.mcdaily.controller;

import com.zftx.mcdaily.constants.SingConstnt;
import com.zftx.mcdaily.util.RSAUtils;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("sign")
public class SignController {

    @RequestMapping("showSign")
    @ResponseBody
    public String showSign( String startTime,String endTime,int pageSize,int pageNum){
        String sign="";
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            String privateKey = SingConstnt.PrivateKey;

            String source = "agentNum="+SingConstnt.agentNum+"&startTime="+startTime+"&endTime="+endTime+"&pageSize="+pageSize
            +"&pageNum="+pageNum;

            System.out.println("原文字:" + source);
            byte[] data = source.getBytes();
            sign = RSAUtils.sign(data, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }
}
