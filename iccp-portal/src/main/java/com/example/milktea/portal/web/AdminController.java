package com.example.milktea.portal.web;

import com.example.common.result.Result;
import com.example.milktea.portal.service.AdminFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.common.dto.SearchDTO;
import com.example.milktea.pojo.AdminDO;
import com.example.common.util.PageResult;


@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminFeignService adminFeignService;

	@PostMapping("/page")
	public Result<PageResult<AdminDO>> page(@RequestBody SearchDTO<AdminDO> query) {
		PageResult<AdminDO> result = adminFeignService.page(query);
		return Result.ok(result);
	}

	@PostMapping("/save")
	public Result<String> save(@RequestBody AdminDO record){
		if(record.getId() == null){
			adminFeignService.add(record);
		}else{
			adminFeignService.update(record);
		}
		return Result.ok("操作成功!");
	}

	@DeleteMapping("/{id}")
	public Result<String> delete(@PathVariable("id") Long id){
		adminFeignService.delete(id);
		return Result.ok("删除成功!");

	}

	@GetMapping("/{id}")
	public Result<AdminDO> get(@PathVariable("id") Long id){
		AdminDO record = adminFeignService.get(id);
		return Result.ok(record);
	}

	@PostMapping("/query/list")
	public Result<List<AdminDO>> queryList(@RequestBody @Valid AdminDO query) {
		List<AdminDO> result = adminFeignService.listBy(query);
		return Result.ok(result);
	}

	@PostMapping("/query/object")
	public Result<AdminDO> queryObject(@RequestBody @Valid AdminDO query) {
		AdminDO result = adminFeignService.getBy(query);
		return Result.ok(result);
	}


}

