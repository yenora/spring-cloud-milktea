package com.example.milktea.service.impl;

import static com.example.common.vo.JSONResultVO.CODE_ERROR;
import static com.example.common.vo.JSONResultVO.CODE_SUCCESS;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import java.util.List;
import java.util.Objects;

import com.example.common.dto.SearchDTO;
import com.example.common.util.PageResult;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.pojo.MemberDO;
import com.example.milktea.pojo.OrdersDO;
import com.example.milktea.pojo.OrdersDOExample;
import com.example.milktea.pojo.OrdersDetailDO;
import com.example.milktea.service.MemberService;
import com.example.milktea.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.OrdersDOMapper;
import com.example.milktea.pojo.OrdersDOExample.Criteria;
import com.example.milktea.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDOMapper ordersMapper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrdersDetailService ordersDetailService;

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO pageList(SearchDTO<OrdersDO> query) {
        OrdersDOExample example = new OrdersDOExample();
        Criteria criteria = example.createCriteria();
		if (query.getEntity().getMemberId() != null) {
			criteria.andMemberIdEqualTo(query.getEntity().getMemberId());
		}
		if (query.getEntity().getAmount() != null) {
			criteria.andAmountGreaterThanOrEqualTo(query.getEntity().getAmount());
		}
		if(query.getEntity().getQueryCreateTimeBegin() != null) {
			criteria.andCreateTimeGreaterThanOrEqualTo(query.getEntity().getQueryCreateTimeBegin());
		}
		if(query.getEntity().getQueryCreateTimeEnd() != null) {
			criteria.andCreateTimeLessThanOrEqualTo(query.getEntity().getQueryCreateTimeEnd());
		}
        PageHelper.startPage(query.getPage(), query.getLimit(), query.getSort());
        List<OrdersDO> list = ordersMapper.selectByExample(example);
        return JSONResultVO.builder()
                .data(PageResult.build(new PageInfo<>(genOrderVOList(list))))
                .code(CODE_SUCCESS)
                .message("订单信息分页列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO get(Long id) {
        checkNotNull(id, "param id is null");
        return JSONResultVO.builder()
                .data(ordersMapper.selectByPrimaryKey(id))
                .code(CODE_SUCCESS)
                .message("订单信息详情查询成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO add(OrdersDO record) {
        ordersMapper.insertSelective(record);
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("订单信息添加成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO delete(Long id) {
        checkNotNull(id, "param id is null");
        ordersMapper.deleteByPrimaryKey(id);
        ordersDetailService.deleteBy(OrdersDetailDO.builder().orderId(id).build());
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("订单信息删除成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO update(OrdersDO record) {
        checkNotNull(record.getId(), "record's id is null");
        ordersMapper.updateByPrimaryKeySelective(record);
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("订单信息修改成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO updateCAS(OrdersDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO listBy(OrdersDO query) {
        OrdersDOExample example = new OrdersDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        return JSONResultVO.builder()
                .data(ordersMapper.selectByExample(example))
                .code(CODE_SUCCESS)
                .message("订单信息列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO getBy(OrdersDO query) {
        OrdersDOExample example = new OrdersDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        List<OrdersDO> result = ordersMapper.selectByExample(example);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message("订单信息列表查询失败").build();
        }
        return JSONResultVO.builder()
                .data(result.get(0))
                .code(CODE_SUCCESS)
                .message("订单信息列表查询成功").build();
    }

    private List<OrdersDO> genOrderVOList(List<OrdersDO> orderList) {
        orderList.forEach(order -> {
            MemberDO member = (MemberDO) memberService.get(order.getMemberId()).getData();
            if (Objects.nonNull(member)) {
                order.setMember(member);
            }
        });
        return orderList;
    }
}

