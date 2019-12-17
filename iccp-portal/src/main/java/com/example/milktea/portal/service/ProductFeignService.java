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

import com.example.milktea.pojo.ProductDO;
import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.milktea.portal.service.fallback.ProductFeignFallback;

@FeignClient(value = "ICCP-MILK-REST", fallback = ProductFeignFallback.class)
public interface ProductFeignService {

	@PostMapping("/iccp-milk-rest/v1/product/pageResult")
	public PageResult<ProductDO> page(@RequestBody SearchDTO<ProductDO> query);

	@GetMapping("/iccp-milk-rest/v1/product/{id}")
	public ProductDO get(@PathVariable("id") Long id);

	@PostMapping("/iccp-milk-rest/v1/product/add")
	public Long add(@RequestBody @Valid ProductDO entity);

	@PutMapping("/iccp-milk-rest/v1/product/update")
	public void update(@RequestBody @Valid ProductDO entity);

	@DeleteMapping("/iccp-milk-rest/v1/product/{id}")
	public void delete(@PathVariable("id") Long id);

	@PostMapping("/iccp-milk-rest/v1/product/query/list")
	public List<ProductDO> listBy(@RequestBody @Valid ProductDO query);

	@PostMapping("/iccp-milk-rest/v1/product/query/object")
	public ProductDO getBy(@RequestBody @Valid ProductDO query);


}