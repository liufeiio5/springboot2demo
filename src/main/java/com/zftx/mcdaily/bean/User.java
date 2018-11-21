package com.zftx.mcdaily.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *用户表
 */

@Data
@Accessors(chain = true)
public class User
{
    Integer id;//用户id
    String userName;//用户姓名
    String password;//密码
    String email;//邮箱
    String phone;//手机号
    String birthplace;//籍贯
    String birthday;//生日
    String position;//职位

}
