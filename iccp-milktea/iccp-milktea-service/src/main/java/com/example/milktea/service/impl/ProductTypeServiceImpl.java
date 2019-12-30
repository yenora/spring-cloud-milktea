package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.List;

import com.example.common.vo.JSONResultVO;
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
	public JSONResultVO get(Long id) {
		checkNotNull(id, "param id is null");
		return JSONResultVO.builder()
				.data(productTypeMapper.selectByPrimaryKey(id))
				.code(1)
				.msg("产品种类信息详情查询成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO add(ProductTypeDO record) {
		return JSONResultVO.builder()
				.data(productTypeMapper.insertSelective(record))
				.code(1)
				.msg("产品种类信息添加成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO delete(Long id) {
		checkNotNull(id, "param id is null");
		return JSONResultVO.builder()
				.data(productTypeMapper.deleteByPrimaryKey(id))
				.code(1)
				.msg("产品种类信息删除成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO update(ProductTypeDO record) {
		checkNotNull(record.getId(), "record's id is null");
		return JSONResultVO.builder()
				.data(productTypeMapper.updateByPrimaryKeySelective(record))
				.code(1)
				.msg("产品种类信息修改成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO updateCAS(ProductTypeDO record) {
		throw new IllegalAccessError("无法访问的方法");
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO listBy(ProductTypeDO query) {
		ProductTypeDOExample example = new ProductTypeDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		return JSONResultVO.builder()
				.data(productTypeMapper.selectByExample(example))
				.code(1)
				.msg("产品种类信息列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO getBy(ProductTypeDO query) {
		ProductTypeDOExample example = new ProductTypeDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		List<ProductTypeDO> result = productTypeMapper.selectByExample(example);
		checkState(result.size()<2, "multy result by query");
		if(result.isEmpty()){
			return JSONResultVO.builder()
					.data(null)
					.code(-1)
					.msg("产品种类信息列表查询失败").build();
		}
		return JSONResultVO.builder()
				.data(result.get(0))
				.code(1)
				.msg("产品种类信息列表查询成功").build();
	}
}

