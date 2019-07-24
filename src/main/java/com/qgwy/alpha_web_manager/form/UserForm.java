package com.qgwy.alpha_web_manager.form;

import lombok.Data;


import java.io.Serializable;

@Data
public class UserForm implements Serializable {
    private String username;
    private String password;
}
