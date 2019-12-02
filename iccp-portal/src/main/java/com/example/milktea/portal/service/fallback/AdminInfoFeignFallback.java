package com.example.milktea.portal.service.fallback;

import dto.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.example.milktea.pojo.AdminInfoDO;
import com.example.milktea.portal.service.AdminInfoFeignService;
import util.PageResult;

@Component
public class AdminInfoFeignFallback implements AdminInfoFeignService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminInfoFeignFallback.class);

	@Override
	public PageResult<AdminInfoDO> page(SearchDTO<AdminInfoDO> search) {
		LOGGER.error("call remote AdminInfoFeignService  failed!");
		return new PageResult<AdminInfoDO>();
	}

	@Override
	public AdminInfoDO get(Long id) {
		LOGGER.error("call remote AdminInfoFeignService  failed!");
		return new AdminInfoDO();
	}

	@Override
	public Long add(AdminInfoDO record) {
		LOGGER.error("call remote AdminInfoFeignService  failed!");
		return 0L;
	}

	@Override
	public void delete(Long id) {
		LOGGER.error("call remote AdminInfoFeignService  failed!");
	}

	@Override
	public void update(AdminInfoDO record) {
		LOGGER.error("call remote AdminInfoFeignService  failed!");
	}

	@Override
	public List<AdminInfoDO> listBy(AdminInfoDO query) {
		LOGGER.error("call remote AdminInfoFeignService  failed!");
		return Lists.newArrayList();
	}

	@Override
	public AdminInfoDO getBy(AdminInfoDO query) {
		LOGGER.error("call remote AdminInfoFeignService  failed!");
		return new AdminInfoDO();
	}


}