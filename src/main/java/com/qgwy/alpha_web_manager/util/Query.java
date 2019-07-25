package com.qgwy.alpha_web_manager.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	// 
	private Integer currPage;
	// 每页条数
	private Integer limit;

	public Query(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		if(params.get("currPage") != null && params.get("limit") != null){
			this.currPage = Integer.parseInt(params.get("currPage").toString());
			this.limit = Integer.parseInt(params.get("limit").toString());
			this.put("offset", (currPage - 1) * limit);
			this.put("limit", limit);
		}
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public int getLimit() {
		return limit;
	}

}
