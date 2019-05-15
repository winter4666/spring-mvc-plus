package com.github.winter4666.springmvcplus.model;

import java.util.List;

/**
 * 分页模型，包含分页数据，及数据总数
 * @author wutian
 */
public class PageResult<T> {
	
	private List<T> data;
	private long total;
	
	public PageResult(List<T> data, long total) {
		super();
		this.data = data;
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public long getTotal() {
		return total;
	}

}
