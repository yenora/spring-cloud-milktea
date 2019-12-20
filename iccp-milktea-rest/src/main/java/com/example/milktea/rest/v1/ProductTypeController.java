package com.example.milktea.rest.v1;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.milktea.pojo.ProductStapleDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.common.log.AutoLog;
import com.example.common.util.PageResult;
import com.example.milktea.pojo.ProductTypeDO;
import com.example.milktea.service.ProductTypeService;
import com.example.common.dto.SearchDTO;

@Controller
@RequestMapping("/v1/product/type")
public class ProductTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeController.class);
    @Autowired
    private ProductTypeService productTypeService;

    @AutoLog(value = "调用产品种类信息分页列表查询接口")
    @PostMapping("/pageResult")
    public ResponseEntity<PageResult<ProductTypeDO>> pageResult(@RequestBody SearchDTO<ProductTypeDO> query) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息分页列表查询接口");
        }
        PageResult<ProductTypeDO> pageResult = PageResult.build(productTypeService.pageList(query));
        return ResponseEntity.ok(pageResult);
    }

    @AutoLog(value = "调用产品种类信息详情查询接口")
    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeDO> object(@PathVariable("id") Long id) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息详情查询接口");
        }
        ProductTypeDO result = productTypeService.get(id);
        return ResponseEntity.ok(result);
    }

    @AutoLog(value = "调用产品种类信息添加接口")
    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody @Valid ProductTypeDO productType) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息添加接口");
        }
        productTypeService.add(productType);
        return ResponseEntity.ok().build();
    }

    @AutoLog(value = "调用产品种类信息删除接口")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息删除接口");
        }
        productTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @AutoLog(value = "调用产品种类信息修改接口")
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody @Valid ProductTypeDO productType) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息修改接口");
        }
        //you can call updateCAS method while DO object has version field!
        productTypeService.update(productType);
        return ResponseEntity.ok().build();

    }

    @AutoLog(value = "调用产品种类信息列表查询接口")
    @PostMapping("/query/list")
    public ResponseEntity<List<ProductTypeDO>> queryList(@RequestBody @Valid ProductTypeDO query) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息列表查询接口");
        }
        List<ProductTypeDO> result = productTypeService.listBy(query);
        return ResponseEntity.ok(result);

    }

    @AutoLog(value = "调用产品种类信息列表查询接口")
    @PostMapping("/query/object")
    public ResponseEntity<ProductTypeDO> queryObject(@RequestBody @Valid ProductTypeDO query) throws Exception {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用产品种类信息列表查询接口");
        }
        ProductTypeDO result = productTypeService.getBy(query);
        return ResponseEntity.ok(result);

    }
}

