package com.zftx.mcdaily.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * 数据库表数据转json
 */
@Slf4j
public class TableToJson {

    public static void saveDataToFile(String fileName, List data){

        //将传入的参数转换为json格式
        String json = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        BufferedWriter writer = null;
        File file = new File(System.getProperty("user.dir")+"/src/main/resources/static/json/"+fileName+".json");
        if(!file.exists()){
            try{
                file.createNewFile();

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //写入
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8"));
            writer.write(json);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //日志信息
        log.info(Thread.currentThread().getStackTrace()[1].getMethodName()+
                " && "+ "fileName:"+fileName+" data:"+json+" ## message:"+R.ok("转换json成功"));
    }


}
