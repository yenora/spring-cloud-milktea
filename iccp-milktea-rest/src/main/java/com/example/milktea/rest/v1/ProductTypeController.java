package com.example.milktea.rest.v1;

import javax.validation.Valid;

import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.pojo.ProductDO;
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

    @AutoLog(value="调用产品种类信息分页列表查询接口")
    @PostMapping("/pageResult")
    public ResponseEntity<JSONResultVO> pageResult(@RequestBody SearchDTO<ProductTypeDO> query) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息分页列表查询接口");
        }
        JSONResultVO result = productTypeService.pageList(query);
        return ResponseEntity.ok(result);
    }


    @AutoLog(value = "调用产品种类信息列表查询接口")
    @PostMapping("/query/list")
    public ResponseEntity<JSONResultVO> queryList(@RequestBody @Valid ProductTypeDO query) throws Exception {
            if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息列表查询接口");
        }
        JSONResultVO result = productTypeService.listBy(query);
        return ResponseEntity.ok(result);

    }

    @AutoLog(value="调用产品种类信息添加接口")
    @PostMapping("/add")
    public ResponseEntity<JSONResultVO> add(@RequestBody @Valid ProductTypeDO product) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品信息添加接口");
        }
        JSONResultVO result = productTypeService.add(product);
        return ResponseEntity.ok(result);
    }

    @AutoLog(value="调用产品种类信息删除接口")
    @DeleteMapping("/{id}")
    public ResponseEntity<JSONResultVO> delete(@PathVariable("id") String id) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品信息删除接口");
        }
        JSONResultVO result = productTypeService.delete(Long.parseLong(id));
        return ResponseEntity.ok(result);
    }

    @AutoLog(value="调用产品种类信息修改接口")
    @PutMapping("/update")
    public ResponseEntity<JSONResultVO> update(@RequestBody @Valid ProductTypeDO product) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品信息修改接口");
        }
        //you can call updateCAS method while DO object has version field!
        JSONResultVO result = productTypeService.update(product);
        return ResponseEntity.ok(result);

    }
}

