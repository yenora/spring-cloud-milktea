package com.example.milktea.service.impl;

import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.mapper.MemberDOMapper;
import com.example.milktea.pojo.MemberDO;
import com.example.milktea.pojo.MemberDOExample;
import com.example.milktea.pojo.MemberDOExample.Criteria;
import com.example.milktea.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.example.common.vo.JSONResultVO.CODE_ERROR;
import static com.example.common.vo.JSONResultVO.CODE_SUCCESS;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

@Service
public class MemberServiceImpl implements MemberService {

	private static final String DEFAULT_AVATAR = "http://127.0.0.1:5000/spring-cloud-milktea/default/avatar.gif";
	private static final String DEFAULT_PASSWORD = "123456";

	@Autowired
	private MemberDOMapper memberDOMapper;

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO pageList(SearchDTO<MemberDO> query) {
		MemberDOExample example = new MemberDOExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(query.getEntity().getName())) {
			criteria.andNameEqualTo(query.getEntity().getName());
		}
		if(query.getEntity().getQueryCreateTimeBegin() != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(query.getEntity().getQueryCreateTimeBegin());
		}

		if(query.getEntity().getQueryCreateTimeEnd() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(query.getEntity().getQueryCreateTimeEnd());
		}
		PageHelper.startPage(query.getPage(), query.getLimit(), query.getSort());
		List<MemberDO> list = memberDOMapper.selectByExample(example);
		// 把密码清空
		list.forEach(p -> p.setPassword(null));
		return JSONResultVO.builder()
				.data(PageResult.build(new PageInfo<>(list)))
				.code(CODE_SUCCESS)
				.message("客户信息分页列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO get(Long id) {
		checkNotNull(id, "param id is null");
		return JSONResultVO.builder()
				.data(memberDOMapper.selectByPrimaryKey(id))
				.code(CODE_SUCCESS)
				.message("客户信息详情查询成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO add(MemberDO record) {
		checkNotNull(record.getName(), "param name is null");
		if (checkNameUnique(record.getName())) {
			if (record.getCreateTime() == null) {
				record.setCreateTime(LocalDateTime.now());
			}
			if (record.getRecentLoginTime() == null) {
				record.setRecentLoginTime(LocalDateTime.now());
			}
			if (StringUtils.isBlank(record.getAvatar())) {
				record.setAvatar(DEFAULT_AVATAR);
			}
			if (StringUtils.isBlank(record.getPassword())) {
				record.setPassword(DigestUtils.md5DigestAsHex(DEFAULT_PASSWORD.getBytes()));
			} else {
				record.setPassword(DigestUtils.md5DigestAsHex(record.getPassword().getBytes()));
			}
			record.setFrequency(0);
			memberDOMapper.insertSelective(record);
			return JSONResultVO.builder()
					.code(CODE_SUCCESS)
					.message("客户信息添加成功").build();
		} else {
			return JSONResultVO.builder()
					.code(CODE_ERROR)
					.message("客户名称重复，请重新输入").build();
		}
	}

	@Override
	@Transactional
	public JSONResultVO delete(Long id) {
		checkNotNull(id, "param id is null");
		memberDOMapper.deleteByPrimaryKey(id);
		return JSONResultVO.builder()
				.code(CODE_SUCCESS)
				.message("客户信息删除成功").build();
	}

	@Override
	@Transactional
	public JSONResultVO update(MemberDO record) {
		checkNotNull(record.getId(), "record id is null");
		checkNotNull(record.getName(), "param name is null");
		if (checkNameUnique(record.getName())) {
			memberDOMapper.updateByPrimaryKeySelective(record);
			return JSONResultVO.builder()
					.code(CODE_SUCCESS)
					.message("客户信息修改成功").build();
		} else {
			return JSONResultVO.builder()
					.code(CODE_SUCCESS)
					.message("客户名称重复，请重新输入").build();
		}
	}

	@Override
	@Transactional
	public JSONResultVO updateCAS(MemberDO record) {
		throw new IllegalAccessError("无法访问的方法");
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO listBy(MemberDO query) {
		MemberDOExample example = new MemberDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		return JSONResultVO.builder()
				.data(memberDOMapper.selectByExample(example))
				.code(CODE_SUCCESS)
				.message("客户信息列表查询成功").build();
	}

	@Override
	@Transactional(readOnly = true)
	public JSONResultVO getBy(MemberDO query) {
		MemberDOExample example = new MemberDOExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(query.getName())) {
			criteria.andNameEqualTo(query.getName());
		}
		List<MemberDO> result = memberDOMapper.selectByExample(example);
		checkState(result.size()<2, "multy result by query");
		if (result.isEmpty()) {
			return JSONResultVO.builder()
					.code(CODE_ERROR)
					.message("客户信息列表查询失败").build();
		}
		return JSONResultVO.builder()
				.data(result.get(0))
				.code(CODE_SUCCESS)
				.message("客户信息列表查询成功").build();
	}

	private boolean checkNameUnique(String name) {
		MemberDO record = (MemberDO) ((MemberService) AopContext.currentProxy()).getBy(MemberDO.builder().name(name).build()).getData();
		return Objects.isNull(record);
	}
}

