package util;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * @Description:
 * @Author: yaos
 * @Date: 2019-11-29 09:50
 * @Version: V1.0
 **/
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;

    //总记录数
    private long total;
    //总页数
    private int pages;
    //结果集
    private List<T> list;
    
    public PageResult(){}
    
	public PageResult(int pageNum, int pageSize, int size, long total, int pages, List<T> list) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.size = size;
		this.total = total;
		this.pages = pages;
		this.list = list;
	}
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    public static<T> PageResult<T> build(PageInfo<T> pageInfo){
    	return new PageResult<T>(pageInfo.getPageNum(), pageInfo.getPageSize(), 
    			pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
    }

    

    
}