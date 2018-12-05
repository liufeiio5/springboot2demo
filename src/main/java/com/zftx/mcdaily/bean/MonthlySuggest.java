package com.zftx.mcdaily.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 建议
 */
@Data
@Accessors(chain = true)
public class MonthlySuggest {

     Integer id;
     Integer suggestId;   //建议id
     String  suggestContent;  //建议内容

}
