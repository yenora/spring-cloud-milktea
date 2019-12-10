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
import com.example.milktea.mapper.AdminDOMapper;
import com.example.milktea.pojo.AdminDO;
import com.example.milktea.pojo.AdminDOExample;
import com.example.milktea.pojo.AdminDOExample.Criteria;
import com.example.milktea.service.AdminService;
import com.example.common.dto.SearchDTO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDOMapper adminMapper;

	@Override
	@Transactional(readOnly = true)
	public PageInfo<AdminDO> pageList(SearchDTO<AdminDO> query) {
		AdminDOExample example = new AdminDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		PageHelper.startPage(query.getPage(), query.getRows());
		List<AdminDO> list = adminMapper.selectByExample(example);
		PageInfo<AdminDO> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public AdminDO get(Long id) {
		checkNotNull(id, "param id is null");
		return adminMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int add(AdminDO record) {
		return adminMapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int delete(Long id) {
		checkNotNull(id, "param id is null");
		return adminMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int update(AdminDO record) {
		checkNotNull(record.getId(), "record's id is null");
		return adminMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional
	public int updateCAS(AdminDO record) {
		throw new IllegalAccessError("无法访问的方法");
	}

	@Override
	@Transactional(readOnly = true)
	public List<AdminDO> listBy(AdminDO query) {
		AdminDOExample example = new AdminDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		return adminMapper.selectByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public AdminDO getBy(AdminDO query) {
		AdminDOExample example = new AdminDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		List<AdminDO> result = adminMapper.selectByExample(example);
		checkState(result.size()<2, "multy result by query");
		if(result.isEmpty()){
			return null;
		}
		return result.get(0);
	}
}

