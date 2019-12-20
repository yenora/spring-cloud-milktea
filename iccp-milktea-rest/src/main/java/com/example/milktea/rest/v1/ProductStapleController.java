package com.example.milktea.rest.v1;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.milktea.pojo.ProductTypeDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.common.log.AutoLog;
import com.example.common.util.PageResult;
import com.example.milktea.pojo.ProductStapleDO;
import com.example.milktea.service.ProductStapleService;
import com.example.common.dto.SearchDTO;

@Controller
@RequestMapping("/v1/product/staple")
public class ProductStapleController {

private static final Logger LOGGER = LoggerFactory.getLogger(ProductStapleController.class);
	@Autowired
	private ProductStapleService productStapleService;

	@AutoLog(value="调用产品原料信息分页列表查询接口")
	@PostMapping("/pageResult")
	public ResponseEntity<PageResult<ProductStapleDO>> pageResult(@RequestBody SearchDTO<ProductStapleDO> query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品原料信息分页列表查询接口");
		}
		PageResult<ProductStapleDO> pageResult = PageResult.build(productStapleService.pageList(query));
		return ResponseEntity.ok(pageResult);
	}

	@AutoLog(value="调用产品原料信息详情查询接口")
	@GetMapping("/{id}")
	public ResponseEntity<ProductStapleDO> object(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品原料信息详情查询接口");
		}
		ProductStapleDO result = productStapleService.get(id);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用产品原料信息添加接口")
	@PostMapping("/add")
	public ResponseEntity<Void> add(@RequestBody @Valid ProductStapleDO productStaple) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品原料信息添加接口");
		}
		productStapleService.add(productStaple);
		return ResponseEntity.ok().build();
	}

	@AutoLog(value="调用产品原料信息删除接口")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品原料信息删除接口");
		}
		productStapleService.delete(id);
		return ResponseEntity.ok().build();
	}

	@AutoLog(value="调用产品原料信息修改接口")
	@PutMapping("/update")
	public ResponseEntity<Void> update(@RequestBody @Valid ProductStapleDO productStaple) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品原料信息修改接口");
		}
		//you can call updateCAS method while DO object has version field!
		productStapleService.update(productStaple);
		return ResponseEntity.ok().build();

	}

	@AutoLog(value="调用产品原料信息列表查询接口")
	@PostMapping("/query/list")
	public ResponseEntity<List<ProductStapleDO>> queryList(@RequestBody @Valid ProductStapleDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品原料信息列表查询接口");
		}
		List<ProductStapleDO> result = productStapleService.listBy(query);
		return ResponseEntity.ok(result);

	}

	@AutoLog(value="调用产品原料信息列表查询接口")
	@PostMapping("/query/object")
	public ResponseEntity<ProductStapleDO> queryObject(@RequestBody @Valid ProductStapleDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品原料信息列表查询接口");
		}
		ProductStapleDO result = productStapleService.getBy(query);
		return ResponseEntity.ok(result);
	}
}

