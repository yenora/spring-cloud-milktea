package com.example.milktea.service;

import com.example.common.service.AbstractService;
import com.example.common.vo.JSONResultVO;
import com.example.milktea.pojo.AdminDO;

public interface AdminService extends AbstractService<AdminDO>{
    JSONResultVO login(AdminDO adminDO);
}