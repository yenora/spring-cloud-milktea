package com.example.milktea.service.impl;

import static com.example.common.vo.JSONResultVO.CODE_ERROR;
import static com.example.common.vo.JSONResultVO.CODE_SUCCESS;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.List;

import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.common.vo.JSONResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.OrderDOMapper;
import com.example.milktea.pojo.OrderDO;
import com.example.milktea.pojo.OrderDOExample;
import com.example.milktea.pojo.OrderDOExample.Criteria;
import com.example.milktea.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDOMapper orderMapper;

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO pageList(SearchDTO<OrderDO> query) {
		OrderDOExample example = new OrderDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		PageHelper.startPage(query.getPage(), query.getLimit(), query.getSort());
		List<OrderDO> list = orderMapper.selectByExample(example);
		return JSONResultVO.builder()
				.data(PageResult.build(new PageInfo<>(list)))
				.code(CODE_SUCCESS)
				.message("订单信息分页列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO get(Long id) {
		checkNotNull(id, "param id is null");
		return JSONResultVO.builder()
				.data(orderMapper.selectByPrimaryKey(id))
				.code(CODE_SUCCESS)
				.message("订单信息详情查询成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO add(OrderDO record) {
		orderMapper.insertSelective(record);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("订单信息添加成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO delete(Long id) {
		checkNotNull(id, "param id is null");
		orderMapper.deleteByPrimaryKey(id);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("订单信息删除成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO update(OrderDO record) {
		checkNotNull(record.getId(), "record's id is null");
		orderMapper.updateByPrimaryKeySelective(record);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("订单信息修改成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO updateCAS(OrderDO record) {
		throw new IllegalAccessError("无法访问的方法");
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO listBy(OrderDO query) {
		OrderDOExample example = new OrderDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		return JSONResultVO.builder()
				.data(orderMapper.selectByExample(example))
				.code(CODE_SUCCESS)
				.message("订单信息列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO getBy(OrderDO query) {
		OrderDOExample example = new OrderDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		List<OrderDO> result = orderMapper.selectByExample(example);
		checkState(result.size()<2, "multy result by query");
		if (result.isEmpty()) {
			return JSONResultVO.builder()
					.code(CODE_ERROR)
					.message("订单信息列表查询失败").build();
		}
		return JSONResultVO.builder()
				.data(result.get(0))
				.code(CODE_SUCCESS)
				.message("订单信息列表查询成功").build();
	}
}

