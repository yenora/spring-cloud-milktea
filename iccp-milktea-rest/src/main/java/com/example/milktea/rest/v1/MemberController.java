package com.example.milktea.rest.v1;

import com.example.common.dto.SearchDTO;
import com.example.common.log.AutoLog;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.pojo.MemberDO;
import com.example.milktea.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1/member")
public class MemberController {

private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;

	@AutoLog(value="调用客户信息分页列表查询接口")
	@PostMapping("/pageResult")
	public ResponseEntity<JSONResultVO> pageResult(@RequestBody SearchDTO<MemberDO> query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户信息分页列表查询接口");
		}
		JSONResultVO pageResult = memberService.pageList(query);
		return ResponseEntity.ok(pageResult);
	}

	@AutoLog(value="调用客户信息详情查询接口")
	@GetMapping("/{id}")
	public ResponseEntity<JSONResultVO> object(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户信息详情查询接口");
		}
		JSONResultVO result = memberService.get(id);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户信息添加接口")
	@PostMapping("/add")
	public ResponseEntity<JSONResultVO> add(@RequestBody @Valid MemberDO order) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户信息添加接口");
		}
		JSONResultVO result = memberService.add(order);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户信息删除接口")
	@DeleteMapping("/{id}")
	public ResponseEntity<JSONResultVO> delete(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户信息删除接口");
		}
		JSONResultVO result = memberService.delete(id);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户信息修改接口")
	@PutMapping("/update")
	public ResponseEntity<JSONResultVO> update(@RequestBody @Valid MemberDO order) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户信息修改接口");
		}
		//you can call updateCAS method while DO object has version field!
		JSONResultVO result = memberService.update(order);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户信息列表查询接口")
	@PostMapping("/query/list")
	public ResponseEntity<JSONResultVO> queryList(@RequestBody @Valid MemberDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户信息列表查询接口");
		}
		JSONResultVO result = memberService.listBy(query);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户信息列表查询接口")
	@PostMapping("/query/object")
	public ResponseEntity<JSONResultVO> queryObject(@RequestBody @Valid MemberDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户信息列表查询接口");
		}
		JSONResultVO result = memberService.getBy(query);
		return ResponseEntity.ok(result);
	}
}

