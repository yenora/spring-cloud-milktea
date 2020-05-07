package com.example.milktea.rest.v1;

import javax.validation.Valid;

import com.example.common.vo.JSONResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.common.log.AutoLog;
import com.example.common.util.PageResult;
import com.example.milktea.pojo.ProductDO;
import com.example.milktea.service.ProductService;
import com.example.common.dto.SearchDTO;

@Controller
@RequestMapping("/v1/product")
public class ProductController {

private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	@AutoLog(value="调用产品信息分页列表查询接口")
	@PostMapping("/pageResult")
	public ResponseEntity<JSONResultVO> pageResult(@RequestBody SearchDTO<ProductDO> query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品信息分页列表查询接口");
		}
		JSONResultVO result = productService.pageList(query);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用产品信息添加接口")
	@PostMapping("/add")
	public ResponseEntity<JSONResultVO> add(@RequestBody @Valid ProductDO product) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品信息添加接口");
		}
		JSONResultVO result = productService.add(product);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用产品信息删除接口")
	@DeleteMapping("/{id}")
	public ResponseEntity<JSONResultVO> delete(@PathVariable("id") String id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品信息删除接口");
		}
		JSONResultVO result = productService.delete(Long.parseLong(id));
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用产品信息修改接口")
	@PutMapping("/update")
	public ResponseEntity<JSONResultVO> update(@RequestBody @Valid ProductDO product) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品信息修改接口");
		}
		//you can call updateCAS method while DO object has version field!
		JSONResultVO result = productService.update(product);
		return ResponseEntity.ok(result);

	}
}

