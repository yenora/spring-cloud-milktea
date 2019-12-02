package com.example.milktea.portal.web;

import dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import javax.validation.Valid;
import com.example.milktea.pojo.AdminInfoDO;
import com.example.milktea.portal.service.AdminInfoFeignService;
import org.springframework.web.bind.annotation.*;
import result.Result;
import util.PageResult;


@RestController
@RequestMapping("/admin/info")
public class AdminInfoController {

	@Autowired
	private AdminInfoFeignService adminInfoFeignService;

	@PostMapping("/page")
	public Result<PageResult<AdminInfoDO>> page(@RequestBody SearchDTO<AdminInfoDO> query) {
//		query.getEntity().adorn();
		PageResult<AdminInfoDO> result = adminInfoFeignService.page(query);
		return Result.ok(result);
	}

	@PostMapping("/save")
	public Result<String> save(@RequestBody AdminInfoDO record){
//		record.adorn();
		if(record.getId() == null){
			adminInfoFeignService.add(record);
		}else{
			adminInfoFeignService.update(record);
		}
		return Result.ok("操作成功!");
	}

	@DeleteMapping("/{id}")
	public Result<String> delete(@PathVariable("id") Long id){
		adminInfoFeignService.delete(id);
		return Result.ok("删除成功!");

	}

	@GetMapping("/{id}")
	public Result<AdminInfoDO> get(@PathVariable("id") Long id){
		AdminInfoDO record = adminInfoFeignService.get(id);
		return Result.ok(record);
	}

	@PostMapping("/query/list")
	public Result<List<AdminInfoDO>> queryList(@RequestBody @Valid AdminInfoDO query) {
		List<AdminInfoDO> result = adminInfoFeignService.listBy(query);
		return Result.ok(result);
	}

	@PostMapping("/query/object")
	public Result<AdminInfoDO> queryObject(@RequestBody @Valid AdminInfoDO query) {
		AdminInfoDO result = adminInfoFeignService.getBy(query);
		return Result.ok(result);
	}


}

