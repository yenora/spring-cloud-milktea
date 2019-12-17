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

import com.example.milktea.pojo.ProductStapleDO;
import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.milktea.portal.service.fallback.ProductStapleFeignFallback;

@FeignClient(value = "ICCP-MILK-REST", fallback = ProductStapleFeignFallback.class)
public interface ProductStapleFeignService {

	@PostMapping("/iccp-milk-rest/v1/product/staple/pageResult")
	public PageResult<ProductStapleDO> page(@RequestBody SearchDTO<ProductStapleDO> query);

	@GetMapping("/iccp-milk-rest/v1/product/staple/{id}")
	public ProductStapleDO get(@PathVariable("id") Long id);

	@PostMapping("/iccp-milk-rest/v1/product/staple/add")
	public Long add(@RequestBody @Valid ProductStapleDO entity);

	@PutMapping("/iccp-milk-rest/v1/product/staple/update")
	public void update(@RequestBody @Valid ProductStapleDO entity);

	@DeleteMapping("/iccp-milk-rest/v1/product/staple/{id}")
	public void delete(@PathVariable("id") Long id);

	@PostMapping("/iccp-milk-rest/v1/product/staple/query/list")
	public List<ProductStapleDO> listBy(@RequestBody @Valid ProductStapleDO query);

	@PostMapping("/iccp-milk-rest/v1/product/staple/query/object")
	public ProductStapleDO getBy(@RequestBody @Valid ProductStapleDO query);


}