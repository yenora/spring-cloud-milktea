package com.example.milktea.portal.web;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import javax.validation.Valid;
import com.jandar.common.controller.Result;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.common.dto.SearchDTO;
import com.example.milktea.pojo.ProductTypeDO;
import com.example.common.util.PageResult;
import com.example.milktea.portal.service.ProductTypeFeignService;


@RestController
@RequestMapping("/product/type")
public class ProductTypeController {

	@Autowired
	private ProductTypeFeignService productTypeFeignService;

	@PostMapping("/page")
	public Result<PageResult<ProductTypeDO>> page(@RequestBody SearchDTO<ProductTypeDO> query) {
		PageResult<ProductTypeDO> result = productTypeFeignService.page(query);
		return Result.ok(result);
	}

	@PostMapping("/save")
	public Result<String> save(@RequestBody ProductTypeDO record){
		if(record.getId() == null){
			productTypeFeignService.add(record);
		}else{
			productTypeFeignService.update(record);
		}
		return Result.ok("操作成功!");
	}

	@DeleteMapping("/{id}")
	public Result<String> delete(@PathVariable("id") Long id){
		productTypeFeignService.delete(id);
		return Result.ok("删除成功!");

	}

	@GetMapping("/{id}")
	public Result<ProductTypeDO> get(@PathVariable("id") Long id){
		ProductTypeDO record = productTypeFeignService.get(id);
		return Result.ok(record);
	}

	@PostMapping("/query/list")
	public Result<List<ProductTypeDO>> queryList(@RequestBody @Valid ProductTypeDO query) {
		List<ProductTypeDO> result = productTypeFeignService.listBy(query);
		return Result.ok(result);
	}

	@PostMapping("/query/object")
	public Result<ProductTypeDO> queryObject(@RequestBody @Valid ProductTypeDO query) {
		ProductTypeDO result = productTypeFeignService.getBy(query);
		return Result.ok(result);
	}


}

