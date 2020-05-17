package com.example.milktea.service.impl;

import static com.example.common.vo.JSONResultVO.CODE_ERROR;
import static com.example.common.vo.JSONResultVO.CODE_SUCCESS;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.List;

import com.example.common.util.PageResult;
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
	public JSONResultVO pageList(SearchDTO<ProductTypeDO> query) {
		ProductTypeDOExample example = new ProductTypeDOExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(query.getEntity().getName())) {
			criteria.andNameLike(LIKE + query.getEntity().getName() + LIKE);
		}
		PageHelper.startPage(query.getPage(), query.getLimit(), query.getSort());
		List<ProductTypeDO> list = productTypeMapper.selectByExampleWithBLOBs(example);
		return JSONResultVO.builder()
				.data(PageResult.build(new PageInfo<>(list)))
				.code(CODE_SUCCESS)
				.message("产品种类信息分页列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO get(Long id) {
		checkNotNull(id, "param id is null");
		return JSONResultVO.builder()
				.data(productTypeMapper.selectByPrimaryKey(id))
				.code(CODE_SUCCESS)
				.message("产品种类信息详情查询成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO add(ProductTypeDO record) {
		productTypeMapper.insertSelective(record);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("产品种类信息添加成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO delete(Long id) {
		checkNotNull(id, "param id is null");
		productTypeMapper.deleteByPrimaryKey(id);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("产品种类信息删除成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO update(ProductTypeDO record) {
		checkNotNull(record.getId(), "record's id is null");
		productTypeMapper.updateByPrimaryKeySelective(record);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("产品种类信息修改成功").build();
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
				.data(productTypeMapper.selectByExampleWithBLOBs(example))
				.code(CODE_SUCCESS)
				.message("产品种类信息列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO getBy(ProductTypeDO query) {
		ProductTypeDOExample example = new ProductTypeDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		List<ProductTypeDO> result = productTypeMapper.selectByExampleWithBLOBs(example);
		checkState(result.size()<2, "multy result by query");
		if(result.isEmpty()){
			return JSONResultVO.builder()
					.code(CODE_ERROR)
					.message("产品种类信息列表查询失败").build();
		}
		return JSONResultVO.builder()
				.data(result.get(0))
				.code(CODE_SUCCESS)
				.message("产品种类信息列表查询成功").build();
	}
}

