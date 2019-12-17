package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.ProductTypeDOMapper;
import com.example.milktea.pojo.ProductTypeDO;
import com.example.milktea.pojo.ProductTypeDOExample;
import com.example.milktea.pojo.ProductTypeDOExample.Criteria;
import com.example.milktea.service.ProductTypeService;
import com.example.common.dto.SearchDTO;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

	@Autowired
	private ProductTypeDOMapper productTypeMapper;

	@Override
	@Transactional(readOnly = true)
	public PageInfo<ProductTypeDO> pageList(SearchDTO<ProductTypeDO> query) {
		ProductTypeDOExample example = new ProductTypeDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		PageHelper.startPage(query.getPage(), query.getLimit());
		List<ProductTypeDO> list = productTypeMapper.selectByExample(example);
		PageInfo<ProductTypeDO> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public ProductTypeDO get(Long id) {
		checkNotNull(id, "param id is null");
		return productTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int add(ProductTypeDO record) {
		return productTypeMapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int delete(Long id) {
		checkNotNull(id, "param id is null");
		return productTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int update(ProductTypeDO record) {
		checkNotNull(record.getId(), "record's id is null");
		return productTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional
	public int updateCAS(ProductTypeDO record) {
		throw new IllegalAccessError("无法访问的方法");
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductTypeDO> listBy(ProductTypeDO query) {
		ProductTypeDOExample example = new ProductTypeDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		return productTypeMapper.selectByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductTypeDO getBy(ProductTypeDO query) {
		ProductTypeDOExample example = new ProductTypeDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		List<ProductTypeDO> result = productTypeMapper.selectByExample(example);
		checkState(result.size()<2, "multy result by query");
		if(result.isEmpty()){
			return null;
		}
		return result.get(0);
	}
}

