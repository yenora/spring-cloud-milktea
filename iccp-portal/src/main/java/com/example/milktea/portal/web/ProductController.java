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
import com.example.milktea.pojo.ProductDO;
import com.example.common.util.PageResult;
import com.example.milktea.portal.service.ProductFeignService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductFeignService productFeignService;

	@PostMapping("/page")
	public Result<PageResult<ProductDO>> page(@RequestBody SearchDTO<ProductDO> query) {
		PageResult<ProductDO> result = productFeignService.page(query);
		return Result.ok(result);
	}

	@PostMapping("/save")
	public Result<String> save(@RequestBody ProductDO record){
		if(record.getId() == null){
			productFeignService.add(record);
		}else{
			productFeignService.update(record);
		}
		return Result.ok("操作成功!");
	}

	@DeleteMapping("/{id}")
	public Result<String> delete(@PathVariable("id") Long id){
		productFeignService.delete(id);
		return Result.ok("删除成功!");

	}

	@GetMapping("/{id}")
	public Result<ProductDO> get(@PathVariable("id") Long id){
		ProductDO record = productFeignService.get(id);
		return Result.ok(record);
	}

	@PostMapping("/query/list")
	public Result<List<ProductDO>> queryList(@RequestBody @Valid ProductDO query) {
		List<ProductDO> result = productFeignService.listBy(query);
		return Result.ok(result);
	}

	@PostMapping("/query/object")
	public Result<ProductDO> queryObject(@RequestBody @Valid ProductDO query) {
		ProductDO result = productFeignService.getBy(query);
		return Result.ok(result);
	}


}

