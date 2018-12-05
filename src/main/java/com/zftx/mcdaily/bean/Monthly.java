package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 月报
 */
@Data
@Accessors(chain = true)
public class Monthly {
        private Integer id;
        private Integer userId;
        private Integer month;
        private Integer year;
        private Integer summaryId;
        private Integer difficultyId;
        private Integer programmeId;
        private Integer suggestId;
        private Integer remarkId;
}
