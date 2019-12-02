package com.example.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: 查询条件
 * @Author: yaos
 * @Date: 2019-11-29 09:50
 * @Version: V1.0
 **/
@AllArgsConstructor
@Data
public class SearchDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private T entity;
	
	private Integer page;
	
	private Integer rows;
	
	private String orders;
	
	public SearchDTO(){}
	
	public SearchDTO(T entity, Integer page, Integer rows) {
		super();
		this.entity = entity;
		this.page = page;
		this.rows = rows;
	}

	
}
