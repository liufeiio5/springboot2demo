package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *用户表
 */

@Data
@Accessors(chain = true)
public class User
{
    Integer id;//用户id
    String fullName;  //用户真实姓名
    String userName;//用户姓名
    String password;//密码
    String email;//邮箱
    String phone;//手机号
    String birthplace;//籍贯
    String birthday;//生日
    String position;//职位
    String hobby;//兴趣
    String motto;//座右铭

}
