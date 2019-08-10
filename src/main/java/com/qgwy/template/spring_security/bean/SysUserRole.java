package com.qgwy.template.spring_security.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -3256750757278740295L;

    private Integer userId;

    private Integer roleId;
}