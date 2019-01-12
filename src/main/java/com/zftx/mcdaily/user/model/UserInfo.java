package com.zftx.mcdaily.user.model;

import lombok.Data;

@Data
public class UserInfo extends BaseVo{

    long id;
    String roles;
    String realNameState;
    String idCardNo;
    String phone;
    String headImg;
    String nickName;
    String password;
    String userId;
    String userName;


}
