package com.example.demo1.model;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> extends BaseResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 请求查询的页码
     */
    private int pageNum = 1;

    /**
     * 每页显示条数
     */
    private int pageSize=10;

    /**
     * 结果集
     */
    private List<T> result;

    /**
     * 总条数
     */
    private long count = 0;

    /**
     * 总页数
     */
    private long pageCount;
    
    private long startRowIndex;
    private long endRowIndex;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getStartRowIndex() {
		return startRowIndex;
	}
	public void setStartRowIndex(long startRowIndex) {
		this.startRowIndex = startRowIndex;
	}
	public long getEndRowIndex() {
		return endRowIndex;
	}
	public void setEndRowIndex(long endRowIndex) {
		this.endRowIndex = endRowIndex;
	}
}
