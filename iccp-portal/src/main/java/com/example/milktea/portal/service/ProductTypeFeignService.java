package com.example.milktea.portal.service;

import javax.validation.Valid;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.milktea.pojo.ProductTypeDO;
import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.milktea.portal.service.fallback.ProductTypeFeignFallback;

@FeignClient(value = "ICCP-MILK-REST", fallback = ProductTypeFeignFallback.class)
public interface ProductTypeFeignService {

	@PostMapping("/iccp-milk-rest/v1/product/type/pageResult")
	public PageResult<ProductTypeDO> page(@RequestBody SearchDTO<ProductTypeDO> query);

	@GetMapping("/iccp-milk-rest/v1/product/type/{id}")
	public ProductTypeDO get(@PathVariable("id") Long id);

	@PostMapping("/iccp-milk-rest/v1/product/type/add")
	public Long add(@RequestBody @Valid ProductTypeDO entity);

	@PutMapping("/iccp-milk-rest/v1/product/type/update")
	public void update(@RequestBody @Valid ProductTypeDO entity);

	@DeleteMapping("/iccp-milk-rest/v1/product/type/{id}")
	public void delete(@PathVariable("id") Long id);

	@PostMapping("/iccp-milk-rest/v1/product/type/query/list")
	public List<ProductTypeDO> listBy(@RequestBody @Valid ProductTypeDO query);

	@PostMapping("/iccp-milk-rest/v1/product/type/query/object")
	public ProductTypeDO getBy(@RequestBody @Valid ProductTypeDO query);


}