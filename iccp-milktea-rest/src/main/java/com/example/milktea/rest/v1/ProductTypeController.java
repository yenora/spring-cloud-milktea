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
import com.example.milktea.pojo.ProductTypeDO;
import com.example.milktea.service.ProductTypeService;

@Controller
@RequestMapping("/v1/product/type")
public class ProductTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeController.class);
    @Autowired
    private ProductTypeService productTypeService;

    @AutoLog(value = "调用产品种类信息列表查询接口")
    @PostMapping("/query/list")
    public ResponseEntity<JSONResultVO> queryList(@RequestBody @Valid ProductTypeDO query) throws Exception {
            if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息列表查询接口");
        }
        JSONResultVO result = productTypeService.listBy(query);
        return ResponseEntity.ok(result);

    }
}

