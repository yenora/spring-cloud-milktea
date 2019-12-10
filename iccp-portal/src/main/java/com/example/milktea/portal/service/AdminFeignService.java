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

import com.example.milktea.pojo.AdminDO;
import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.milktea.portal.service.fallback.AdminFeignFallback;

@FeignClient(value = "ICCP-MILK-REST", fallback = AdminFeignFallback.class)
public interface AdminFeignService {

	@PostMapping("/iccp-milk-rest/v1/admin/pageResult")
	public PageResult<AdminDO> page(@RequestBody SearchDTO<AdminDO> query);

	@GetMapping("/iccp-milk-rest/v1/admin/{id}")
	public AdminDO get(@PathVariable("id") Long id);

	@PostMapping("/iccp-milk-rest/v1/admin/add")
	public Long add(@RequestBody @Valid AdminDO entity);

	@PutMapping("/iccp-milk-rest/v1/admin/update")
	public void update(@RequestBody @Valid AdminDO entity);

	@DeleteMapping("/iccp-milk-rest/v1/admin/{id}")
	public void delete(@PathVariable("id") Long id);

	@PostMapping("/iccp-milk-rest/v1/admin/query/list")
	public List<AdminDO> listBy(@RequestBody @Valid AdminDO query);

	@PostMapping("/iccp-milk-rest/v1/admin/query/object")
	public AdminDO getBy(@RequestBody @Valid AdminDO query);


}