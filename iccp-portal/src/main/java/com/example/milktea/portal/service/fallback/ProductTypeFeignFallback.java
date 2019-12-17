package com.example.milktea.portal.service.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.milktea.pojo.ProductTypeDO;
import com.example.milktea.portal.service.ProductTypeFeignService;

@Component
public class ProductTypeFeignFallback implements ProductTypeFeignService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeFeignFallback.class); 

	@Override
	public PageResult<ProductTypeDO> page(SearchDTO<ProductTypeDO> search) {
		LOGGER.error("call remote ProductTypeFeignService  failed!");
		return new PageResult<ProductTypeDO>();
	}

	@Override
	public ProductTypeDO get(Long id) {
		LOGGER.error("call remote ProductTypeFeignService  failed!");
		return new ProductTypeDO();
	}

	@Override
	public Long add(ProductTypeDO record) {
		LOGGER.error("call remote ProductTypeFeignService  failed!");
		return 0L;
	}

	@Override
	public void delete(Long id) {
		LOGGER.error("call remote ProductTypeFeignService  failed!");
	}

	@Override
	public void update(ProductTypeDO record) {
		LOGGER.error("call remote ProductTypeFeignService  failed!");
	}

	@Override
	public List<ProductTypeDO> listBy(ProductTypeDO query) {
		LOGGER.error("call remote ProductTypeFeignService  failed!");
		return Lists.newArrayList();
	}

	@Override
	public ProductTypeDO getBy(ProductTypeDO query) {
		LOGGER.error("call remote ProductTypeFeignService  failed!");
		return new ProductTypeDO();
	}


}