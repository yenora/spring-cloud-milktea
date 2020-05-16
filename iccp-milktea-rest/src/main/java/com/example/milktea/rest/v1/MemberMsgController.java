package com.example.milktea.rest.v1;

import javax.validation.Valid;

import com.example.common.dto.SearchDTO;
import com.example.common.log.AutoLog;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.service.MemberMsgService;
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
import com.example.milktea.pojo.MemberMsgDO;

@Controller
@RequestMapping("/v1/member/msg")
public class MemberMsgController {

private static final Logger LOGGER = LoggerFactory.getLogger(MemberMsgController.class);
	@Autowired
	private MemberMsgService memberMsgService;

	@AutoLog(value="调用客户留言信息分页列表查询接口")
	@PostMapping("/pageResult")
	public ResponseEntity<JSONResultVO> pageResult(@RequestBody SearchDTO<MemberMsgDO> query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户留言信息分页列表查询接口");
		}
		JSONResultVO pageResult = memberMsgService.pageList(query);
		return ResponseEntity.ok(pageResult);
	}

	@AutoLog(value="调用客户留言信息详情查询接口")
	@GetMapping("/{id}")
	public ResponseEntity<JSONResultVO> object(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户留言信息详情查询接口");
		}
		JSONResultVO result = memberMsgService.get(id);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户留言信息添加接口")
	@PostMapping("/add")
	public ResponseEntity<JSONResultVO> add(@RequestBody @Valid MemberMsgDO order) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户留言信息添加接口");
		}
		JSONResultVO result = memberMsgService.add(order);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户留言信息删除接口")
	@DeleteMapping("/{id}")
	public ResponseEntity<JSONResultVO> delete(@PathVariable("id") Long id) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户留言信息删除接口");
		}
		JSONResultVO result = memberMsgService.delete(id);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户留言信息修改接口")
	@PutMapping("/update")
	public ResponseEntity<JSONResultVO> update(@RequestBody @Valid MemberMsgDO order) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户留言信息修改接口");
		}
		//you can call updateCAS method while DO object has version field!
		JSONResultVO result = memberMsgService.update(order);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户留言信息列表查询接口")
	@PostMapping("/query/list")
	public ResponseEntity<JSONResultVO> queryList(@RequestBody @Valid MemberMsgDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户留言信息列表查询接口");
		}
		JSONResultVO result = memberMsgService.listBy(query);
		return ResponseEntity.ok(result);
	}

	@AutoLog(value="调用客户留言信息列表查询接口")
	@PostMapping("/query/object")
	public ResponseEntity<JSONResultVO> queryObject(@RequestBody @Valid MemberMsgDO query) throws Exception {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("调用客户留言信息列表查询接口");
		}
		JSONResultVO result = memberMsgService.getBy(query);
		return ResponseEntity.ok(result);
	}
}

