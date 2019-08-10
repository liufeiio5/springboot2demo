package com.qgwy.template.spring_security.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 7081950007398765100L;
    private Integer roleId;

    private Integer permissionId;
}