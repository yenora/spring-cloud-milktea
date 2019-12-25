package com.example.milktea.portal.web;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import javax.validation.Valid;
import com.example.common.controller.Result;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.common.dto.SearchDTO;
import com.example.milktea.pojo.ProductStapleDO;
import com.example.common.util.PageResult;
import com.example.milktea.portal.service.ProductStapleFeignService;


@RestController
@RequestMapping("/product/staple")
public class ProductStapleController {

	@Autowired
	private ProductStapleFeignService productStapleFeignService;

	@PostMapping("/page")
	public Result<PageResult<ProductStapleDO>> page(@RequestBody SearchDTO<ProductStapleDO> query) {
		PageResult<ProductStapleDO> result = productStapleFeignService.page(query);
		return Result.ok(result);
	}

	@PostMapping("/save")
	public Result<String> save(@RequestBody ProductStapleDO record){
		if(record.getId() == null){
			productStapleFeignService.add(record);
		}else{
			productStapleFeignService.update(record);
		}
		return Result.ok("操作成功!");
	}

	@DeleteMapping("/{id}")
	public Result<String> delete(@PathVariable("id") Long id){
		productStapleFeignService.delete(id);
		return Result.ok("删除成功!");

	}

	@GetMapping("/{id}")
	public Result<ProductStapleDO> get(@PathVariable("id") Long id){
		ProductStapleDO record = productStapleFeignService.get(id);
		return Result.ok(record);
	}

	@PostMapping("/query/list")
	public Result<List<ProductStapleDO>> queryList(@RequestBody @Valid ProductStapleDO query) {
		List<ProductStapleDO> result = productStapleFeignService.listBy(query);
		return Result.ok(result);
	}

	@PostMapping("/query/object")
	public Result<ProductStapleDO> queryObject(@RequestBody @Valid ProductStapleDO query) {
		ProductStapleDO result = productStapleFeignService.getBy(query);
		return Result.ok(result);
	}


}

