package com.example.milktea.portal.service;

import javax.validation.Valid;
import java.util.List;

import dto.SearchDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.milktea.pojo.AdminInfoDO;
import com.example.milktea.portal.service.fallback.AdminInfoFeignFallback;
import util.PageResult;

@FeignClient(value = "ICCP-MILKTEA-REST", fallback = AdminInfoFeignFallback.class)
public interface AdminInfoFeignService {

	@PostMapping("/iccp-milktea-rest/v1/admin/info/pageResult")
	public PageResult<AdminInfoDO> page(@RequestBody SearchDTO<AdminInfoDO> query);

	@GetMapping("/iccp-milktea-rest/v1/admin/info/{id}")
	public AdminInfoDO get(@PathVariable("id") Long id);

	@PostMapping("/iccp-milktea-rest/v1/admin/info/add")
	public Long add(@RequestBody @Valid AdminInfoDO entity);

	@PutMapping("/iccp-milktea-rest/v1/admin/info/update")
	public void update(@RequestBody @Valid AdminInfoDO entity);

	@DeleteMapping("/iccp-milktea-rest/v1/admin/info/{id}")
	public void delete(@PathVariable("id") Long id);

	@PostMapping("/iccp-milktea-rest/v1/admin/info/query/list")
	public List<AdminInfoDO> listBy(@RequestBody @Valid AdminInfoDO query);

	@PostMapping("/iccp-milktea-rest/v1/admin/info/query/object")
	public AdminInfoDO getBy(@RequestBody @Valid AdminInfoDO query);


}