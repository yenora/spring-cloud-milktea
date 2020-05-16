package com.example.milktea.service.impl;

import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.mapper.MemberMsgDOMapper;
import com.example.milktea.pojo.MemberMsgDO;
import com.example.milktea.pojo.MemberMsgDOExample;
import com.example.milktea.pojo.MemberMsgDOExample.Criteria;
import com.example.milktea.service.MemberMsgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.common.vo.JSONResultVO.CODE_ERROR;
import static com.example.common.vo.JSONResultVO.CODE_SUCCESS;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

@Service
public class MemberMsgServiceImpl implements MemberMsgService {

	@Autowired
	private MemberMsgDOMapper memberMsgDOMapper;

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO pageList(SearchDTO<MemberMsgDO> query) {
		MemberMsgDOExample example = new MemberMsgDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		PageHelper.startPage(query.getPage(), query.getLimit(), query.getSort());
		List<MemberMsgDO> list = memberMsgDOMapper.selectByExample(example);
		return JSONResultVO.builder()
				.data(PageResult.build(new PageInfo<>(list)))
				.code(CODE_SUCCESS)
				.message("客户留言信息分页列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO get(Long id) {
		checkNotNull(id, "param id is null");
		return JSONResultVO.builder()
				.data(memberMsgDOMapper.selectByPrimaryKey(id))
				.code(CODE_SUCCESS)
				.message("客户留言信息详情查询成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO add(MemberMsgDO record) {
		memberMsgDOMapper.insertSelective(record);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("客户留言信息添加成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO delete(Long id) {
		checkNotNull(id, "param id is null");
		memberMsgDOMapper.deleteByPrimaryKey(id);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("客户留言信息删除成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO update(MemberMsgDO record) {
		checkNotNull(record.getId(), "record's id is null");
		memberMsgDOMapper.updateByPrimaryKeySelective(record);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("客户留言信息修改成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO updateCAS(MemberMsgDO record) {
		throw new IllegalAccessError("无法访问的方法");
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO listBy(MemberMsgDO query) {
		MemberMsgDOExample example = new MemberMsgDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		return JSONResultVO.builder()
				.data(memberMsgDOMapper.selectByExample(example))
				.code(CODE_SUCCESS)
				.message("客户留言信息列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO getBy(MemberMsgDO query) {
		MemberMsgDOExample example = new MemberMsgDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		List<MemberMsgDO> result = memberMsgDOMapper.selectByExample(example);
		checkState(result.size()<2, "multy result by query");
		if (result.isEmpty()) {
			return JSONResultVO.builder()
					.code(CODE_ERROR)
					.message("客户留言信息列表查询失败").build();
		}
		return JSONResultVO.builder()
				.data(result.get(0))
				.code(CODE_SUCCESS)
				.message("客户留言信息列表查询成功").build();
	}
}

