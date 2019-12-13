package com.example.milktea.service;

import com.example.common.service.AbstractService;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.pojo.AdminDO;

public interface AdminService extends AbstractService<AdminDO>{

    /**
     * 登录
     *
     * @param adminDO adminDO
     * @return json字符串
     */
    JSONResultVO login(AdminDO adminDO);

    /**
     * 根据token获取管理员账户信息
     *
     * @param token token
     * @return json字符串
     */
    JSONResultVO info(String token);

    /**
     * 注销账户
     *
     * @param token token
     */
    void logout(String token);
}