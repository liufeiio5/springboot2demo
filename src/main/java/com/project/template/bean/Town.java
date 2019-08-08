package com.project.template.bean;



import lombok.Data;

import java.io.Serializable;

@Data
public class Town implements Serializable {

    private Integer Id;
    private String name;
    private String townId;
    private String countryId;
}
