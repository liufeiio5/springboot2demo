package com.qgwy.template.spring_security.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 7510552869226022669L;

    private int id;

    private String alias;

    private String name;

    private int pid;
}