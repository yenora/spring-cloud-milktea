package com.example.milktea.rest.v1;

import com.example.common.log.AutoLog;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.pojo.OrdersDetailDO;
import com.example.milktea.service.OrdersDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1/orders/detail")
public class OrdersDetailController {

private static final Logger LOGGER = LoggerFactory.getLogger(OrdersDetailController.class);
	@Autowired
	private OrdersDetailService ordersDetailService;

	@AutoLog(value="调用订单详情信息列表查询接口")
	@PostMapping("/query/list")
	public ResponseEntity<JSONResultVO> queryList(@RequestBody @Valid OrdersDetailDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用订单详情信息列表查询接口");
		}
		JSONResultVO result = ordersDetailService.listBy(query);
		return ResponseEntity.ok(result);
	}
}

