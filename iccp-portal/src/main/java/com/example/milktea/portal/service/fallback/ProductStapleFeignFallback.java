package com.example.milktea.portal.service.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.milktea.pojo.ProductStapleDO;
import com.example.milktea.portal.service.ProductStapleFeignService;

@Component
public class ProductStapleFeignFallback implements ProductStapleFeignService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductStapleFeignFallback.class); 

	@Override
	public PageResult<ProductStapleDO> page(SearchDTO<ProductStapleDO> search) {
		LOGGER.error("call remote ProductStapleFeignService  failed!");
		return new PageResult<ProductStapleDO>();
	}

	@Override
	public ProductStapleDO get(Long id) {
		LOGGER.error("call remote ProductStapleFeignService  failed!");
		return new ProductStapleDO();
	}

	@Override
	public Long add(ProductStapleDO record) {
		LOGGER.error("call remote ProductStapleFeignService  failed!");
		return 0L;
	}

	@Override
	public void delete(Long id) {
		LOGGER.error("call remote ProductStapleFeignService  failed!");
	}

	@Override
	public void update(ProductStapleDO record) {
		LOGGER.error("call remote ProductStapleFeignService  failed!");
	}

	@Override
	public List<ProductStapleDO> listBy(ProductStapleDO query) {
		LOGGER.error("call remote ProductStapleFeignService  failed!");
		return Lists.newArrayList();
	}

	@Override
	public ProductStapleDO getBy(ProductStapleDO query) {
		LOGGER.error("call remote ProductStapleFeignService  failed!");
		return new ProductStapleDO();
	}


}