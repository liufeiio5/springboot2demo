package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 周报  困难
 */
@Data
@Accessors(chain = true)
public class WeeklyProgramme {
        private Integer id;
        private Integer programmeId;
        private String  programmeTitle;
        private String  programmeContent;
}
