package com.example.milktea.rest.v1;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jandar.common.log.AutoLog;
import com.jandar.common.util.PageResult;
import com.example.milktea.pojo.AdminInfoDO;
import com.example.milktea.service.AdminInfoService;
import com.jandar.common.dto.SearchDTO;

@Controller
@RequestMapping("/v1/admin/info")
public class AdminInfoController {

private static final Logger LOGGER = LoggerFactory.getLogger(AdminInfoController.class);
	@Autowired
	private AdminInfoService adminInfoService;

	@AutoLog(value="调用管理员信息分页列表查询接口")
	@PostMapping("/pageResult")
	public ResponseEntity<PageResult<AdminInfoDO>> pageResult(@RequestBody SearchDTO<AdminInfoDO> query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息分页列表查询接口");
		}
		PageResult<AdminInfoDO> pageResult = PageResult.build(adminInfoService.pageList(query));
		return ResponseEntity.ok(pageResult);
	}

	@AutoLog(value="调用管理员信息详情查询接口")
	@GetMapping("/{id}")
	public ResponseEntity<AdminInfoDO> object(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息详情查询接口");
		}
		AdminInfoDO result = adminInfoService.get(id);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用管理员信息添加接口")
	@PostMapping("/add")
	public ResponseEntity<Void> add(@RequestBody @Valid AdminInfoDO adminInfo) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息添加接口");
		}
		adminInfoService.add(adminInfo);
		return ResponseEntity.ok().build();
	}

	@AutoLog(value="调用管理员信息删除接口")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息删除接口");
		}
		adminInfoService.delete(id);
		return ResponseEntity.ok().build();
	}

	@AutoLog(value="调用管理员信息修改接口")
	@PutMapping("/update")
	public ResponseEntity<Void> update(@RequestBody @Valid AdminInfoDO adminInfo) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息修改接口");
		}
		//you can call updateCAS method while DO object has version field!
		adminInfoService.update(adminInfo);
		return ResponseEntity.ok().build();

	}

	@AutoLog(value="调用管理员信息列表查询接口")
	@PostMapping("/query/list")
	public ResponseEntity<List<AdminInfoDO>> queryList(@RequestBody @Valid AdminInfoDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息列表查询接口");
		}
		List<AdminInfoDO> result = adminInfoService.listBy(query);
		return ResponseEntity.ok(result);

	}

	@AutoLog(value="调用管理员信息列表查询接口")
	@PostMapping("/query/object")
	public ResponseEntity<AdminInfoDO> queryObject(@RequestBody @Valid AdminInfoDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息列表查询接口");
		}
		AdminInfoDO result = adminInfoService.getBy(query);
		return ResponseEntity.ok(result);

	}
}

