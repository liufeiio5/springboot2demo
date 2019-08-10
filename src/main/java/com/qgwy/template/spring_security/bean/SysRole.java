package com.qgwy.template.spring_security.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 7510551869226022669L;

    private Integer id;

    private String name;
}