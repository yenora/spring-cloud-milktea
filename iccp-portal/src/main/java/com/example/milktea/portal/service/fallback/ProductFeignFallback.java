package com.example.milktea.portal.service.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.milktea.pojo.ProductDO;
import com.example.milktea.portal.service.ProductFeignService;

@Component
public class ProductFeignFallback implements ProductFeignService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductFeignFallback.class); 

	@Override
	public PageResult<ProductDO> page(SearchDTO<ProductDO> search) {
		LOGGER.error("call remote ProductFeignService  failed!");
		return new PageResult<ProductDO>();
	}

	@Override
	public ProductDO get(Long id) {
		LOGGER.error("call remote ProductFeignService  failed!");
		return new ProductDO();
	}

	@Override
	public Long add(ProductDO record) {
		LOGGER.error("call remote ProductFeignService  failed!");
		return 0L;
	}

	@Override
	public void delete(Long id) {
		LOGGER.error("call remote ProductFeignService  failed!");
	}

	@Override
	public void update(ProductDO record) {
		LOGGER.error("call remote ProductFeignService  failed!");
	}

	@Override
	public List<ProductDO> listBy(ProductDO query) {
		LOGGER.error("call remote ProductFeignService  failed!");
		return Lists.newArrayList();
	}

	@Override
	public ProductDO getBy(ProductDO query) {
		LOGGER.error("call remote ProductFeignService  failed!");
		return new ProductDO();
	}


}