package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 方案
 */
@Data
@Accessors(chain = true)
public class MonthlyProgramme {

    Integer id;
    Integer programmeId;   //方案id
    String programmeContent; //方案名称

}
