package com.example.milktea.rest.v1;

import java.util.List;
import javax.validation.Valid;

import com.example.common.vo.JSONResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.common.log.AutoLog;
import com.example.common.util.PageResult;
import com.example.milktea.pojo.AdminDO;
import com.example.milktea.service.AdminService;
import com.example.common.dto.SearchDTO;

@Controller
@RequestMapping("/v1/admin")
public class AdminController {

private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	@Autowired
	private AdminService adminService;

	@AutoLog(value="调用管理员信息分页列表查询接口")
	@PostMapping("/pageResult")
	public ResponseEntity<PageResult<AdminDO>> pageResult(@RequestBody SearchDTO<AdminDO> query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息分页列表查询接口");
		}
		PageResult<AdminDO> pageResult = PageResult.build(adminService.pageList(query));
		return ResponseEntity.ok(pageResult);
	}

	@AutoLog(value="调用管理员信息详情查询接口")
	@GetMapping("/{id}")
	public ResponseEntity<AdminDO> object(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息详情查询接口");
		}
		AdminDO result = adminService.get(id);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用管理员信息添加接口")
	@PostMapping("/add")
	public ResponseEntity<Void> add(@RequestBody @Valid AdminDO admin) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息添加接口");
		}
		adminService.add(admin);
		return ResponseEntity.ok().build();
	}

	@AutoLog(value="调用管理员信息删除接口")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息删除接口");
		}
		adminService.delete(id);
		return ResponseEntity.ok().build();
	}

	@AutoLog(value="调用管理员信息修改接口")
	@PutMapping("/update")
	public ResponseEntity<Void> update(@RequestBody @Valid AdminDO admin) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息修改接口");
		}
		//you can call updateCAS method while DO object has version field!
		adminService.update(admin);
		return ResponseEntity.ok().build();

	}

	@AutoLog(value="调用管理员信息列表查询接口")
	@PostMapping("/query/list")
	public ResponseEntity<List<AdminDO>> queryList(@RequestBody @Valid AdminDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息列表查询接口");
		}
		List<AdminDO> result = adminService.listBy(query);
		return ResponseEntity.ok(result);

	}

	@AutoLog(value="调用管理员信息列表查询接口")
	@PostMapping("/query/object")
	public ResponseEntity<AdminDO> queryObject(@RequestBody @Valid AdminDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息列表查询接口");
		}
		AdminDO result = adminService.getBy(query);
		return ResponseEntity.ok(result);

	}

	@AutoLog(value="调用管理员登录接口")
	@PostMapping("/login")
	public ResponseEntity<JSONResultVO> login(@RequestBody @Valid AdminDO admin) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员登录接口");
		}

		JSONResultVO result = adminService.login(admin);
		return ResponseEntity.ok(result);
	}


	@AutoLog(value="调用管理员信息接口")
	@GetMapping("/info")
	public ResponseEntity<JSONResultVO> info(String token) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员信息接口");
		}

		JSONResultVO result = adminService.info(token);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用管理员注销接口")
	@PostMapping("/logout")
	public ResponseEntity<JSONResultVO> logout(String token) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用管理员注销接口");
		}

		adminService.logout(token);
		return ResponseEntity.ok().build();
	}
}

