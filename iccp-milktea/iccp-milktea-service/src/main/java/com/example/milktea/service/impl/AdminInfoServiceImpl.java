package com.example.milktea.service.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.AdminInfoDOMapper;
import com.example.milktea.pojo.AdminInfoDO;
import com.example.milktea.pojo.AdminInfoDOExample;
import com.example.milktea.pojo.AdminInfoDOExample.Criteria;
import com.example.milktea.service.AdminInfoService;
import com.example.common.dto.SearchDTO;

@Service
public class AdminInfoServiceImpl implements AdminInfoService{

	@Autowired
	private AdminInfoDOMapper adminInfoMapper;

	@Override
	@Transactional(readOnly = true)
	public PageInfo<AdminInfoDO> pageList(SearchDTO<AdminInfoDO> query) {
		AdminInfoDOExample example = new AdminInfoDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		PageHelper.startPage(query.getPage(), query.getRows());
		List<AdminInfoDO> list = adminInfoMapper.selectByExample(example);
		PageInfo<AdminInfoDO> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public AdminInfoDO get(Long id) {
		checkNotNull(id, "param id is null");
		return adminInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int add(AdminInfoDO record) {
		return adminInfoMapper.insertSelective(record);
	}

	@Override
	@Transactional
	public int delete(Long id) {
		checkNotNull(id, "param id is null");
		return adminInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int update(AdminInfoDO record) {
		checkNotNull(record.getId(), "record's id is null");
		return adminInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional
	public int updateCAS(AdminInfoDO record) {
		checkNotNull(record.getId(), "record's id is null");
		return adminInfoMapper.updateByPrimaryKeySelectiveCAS(record);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AdminInfoDO> listBy(AdminInfoDO query) {
		AdminInfoDOExample example = new AdminInfoDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		return adminInfoMapper.selectByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public AdminInfoDO getBy(AdminInfoDO query) {
		AdminInfoDOExample example = new AdminInfoDOExample();
		Criteria criteria = example.createCriteria();
		//TODO edit your query condition
		List<AdminInfoDO> result = adminInfoMapper.selectByExample(example);
		checkState(result.size()<2, "multy com.example.common.result by query");
		if(result.isEmpty()){
			return null;
		}
		return result.get(0);
	}

	@Override
	@Transactional
	public AdminInfoDO login(AdminInfoDO record) {
		checkNotNull(record.getName(), "record's name is null");
		checkNotNull(record.getPassword(), "record's password is null");
		List<AdminInfoDO> result = adminInfoMapper.login(record);
		checkState(result.size()<2, "multy result by query");
		if(result.isEmpty()){
			return null;
		}
		record = result.get(0);
		record.setUpdateTime(LocalDateTime.now());
		if (record.getFreq() == null) {
			record.setFreq(1);
		} else {
			record.setFreq(record.getFreq() + 1);
		}
		((AdminInfoService) AopContext.currentProxy()).update(record);
		return record;
	}
}

