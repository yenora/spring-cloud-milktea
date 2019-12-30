package com.example.milktea.rest.v1;

import javax.validation.Valid;

import com.example.common.vo.JSONResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.common.log.AutoLog;
import com.example.milktea.pojo.ProductStapleDO;
import com.example.milktea.service.ProductStapleService;

@Controller
@RequestMapping("/v1/product/staple")
public class ProductStapleController {

private static final Logger LOGGER = LoggerFactory.getLogger(ProductStapleController.class);
	@Autowired
	private ProductStapleService productStapleService;

	@AutoLog(value="调用产品原料信息列表查询接口")
	@PostMapping("/query/list")
	public ResponseEntity<JSONResultVO> queryList(@RequestBody @Valid ProductStapleDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用产品原料信息列表查询接口");
		}
		JSONResultVO result = productStapleService.listBy(query);
		return ResponseEntity.ok(result);

	}
}

