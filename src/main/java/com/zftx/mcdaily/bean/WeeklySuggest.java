package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 周报  困难
 */
@Data
@Accessors(chain = true)
public class WeeklySuggest {
        private Integer id;
        private Integer suggestId;
        private String  suggestTitle;
        private String  suggestContent;
}
