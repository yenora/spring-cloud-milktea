package com.example.milktea.portal.service.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.milktea.pojo.AdminDO;
import com.example.milktea.portal.service.AdminFeignService;

@Component
public class AdminFeignFallback implements AdminFeignService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminFeignFallback.class); 

	@Override
	public PageResult<AdminDO> page(SearchDTO<AdminDO> search) {
		LOGGER.error("call remote AdminFeignService  failed!");
		return new PageResult<AdminDO>();
	}

	@Override
	public AdminDO get(Long id) {
		LOGGER.error("call remote AdminFeignService  failed!");
		return new AdminDO();
	}

	@Override
	public Long add(AdminDO record) {
		LOGGER.error("call remote AdminFeignService  failed!");
		return 0L;
	}

	@Override
	public void delete(Long id) {
		LOGGER.error("call remote AdminFeignService  failed!");
	}

	@Override
	public void update(AdminDO record) {
		LOGGER.error("call remote AdminFeignService  failed!");
	}

	@Override
	public List<AdminDO> listBy(AdminDO query) {
		LOGGER.error("call remote AdminFeignService  failed!");
		return Lists.newArrayList();
	}

	@Override
	public AdminDO getBy(AdminDO query) {
		LOGGER.error("call remote AdminFeignService  failed!");
		return new AdminDO();
	}

	@Override
	public AdminDO login(AdminDO admin) {
		LOGGER.error("call remote AdminFeignService  failed!");
		return new AdminDO();
	}


}