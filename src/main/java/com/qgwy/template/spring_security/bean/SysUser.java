package com.qgwy.template.spring_security.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -2836223054703407171L;

    private Integer id;

    private String name;

    private String password;
}