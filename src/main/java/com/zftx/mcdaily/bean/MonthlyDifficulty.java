package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 月报 困难
 */
@Data
@Accessors(chain = true)
public class MonthlyDifficulty {
        private Integer id;
        private Integer difficultyId;
        private String difficultyContent;
}
