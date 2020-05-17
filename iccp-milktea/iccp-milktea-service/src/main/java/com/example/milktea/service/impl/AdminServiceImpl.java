package com.example.milktea.service.impl;

import static com.example.common.vo.JSONResultVO.CODE_ERROR;
import static com.example.common.vo.JSONResultVO.CODE_SUCCESS;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.example.common.util.PageResult;
import com.example.common.vo.JSONResultVO;
import com.example.common.util.JsonUtils;
import com.example.milktea.vo.BackTokenVO;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.milktea.mapper.AdminDOMapper;
import com.example.milktea.pojo.AdminDO;
import com.example.milktea.pojo.AdminDOExample;
import com.example.milktea.pojo.AdminDOExample.Criteria;
import com.example.milktea.service.AdminService;
import com.example.common.dto.SearchDTO;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl implements AdminService {

    private static final String DEFAULT_AVATAR = "http://127.0.0.1:5000/spring-cloud-milktea/default/avatar.gif";
    private static final String DEFAULT_PASSWORD = "123456";

    private static final String REDIS_USER_SESSION_KEY = "MILKTEA_TOKEN";
    private static final String SPLIT = ":";
    private static final String ERROR_TIP = "用户名或密码错误";
    // 过期时间为七天
    private static final Integer expiresIn = 1000 * 60 * 60 * 24 * 7;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AdminDOMapper adminMapper;

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO pageList(SearchDTO<AdminDO> query) {
        AdminDOExample example = new AdminDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<AdminDO> list = adminMapper.selectByExample(example);
        // 把密码清空
        list.forEach(p -> p.setPassword(null));
        return JSONResultVO.builder()
                .data(PageResult.build(new PageInfo<>(list)))
                .code(CODE_SUCCESS)
                .message("管理员信息分页列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO get(Long id) {
        checkNotNull(id, "param id is null");
        AdminDO admin = adminMapper.selectByPrimaryKey(id);
        // 把密码清空
        admin.setPassword(null);
        return JSONResultVO.builder()
                .data(admin)
                .code(CODE_SUCCESS)
                .message("管理员信息详情查询成功").build();
    }

    @Override
    @Transactional
    public JSONResultVO add(AdminDO record) {
        checkNotNull(record.getName(), "param name is null");
        if (checkNameUnique(record.getName())) {
            if (record.getCreateTime() == null) {
                record.setCreateTime(LocalDateTime.now());
            }
            if (StringUtils.isBlank(record.getAvatar())) {
                record.setAvatar(DEFAULT_AVATAR);
            }
            if (record.getType() == null) {
                // 默认为观察员
                record.setType(2);
            }

            if (StringUtils.isBlank(record.getPassword())) {
                record.setPassword(DigestUtils.md5DigestAsHex(DEFAULT_PASSWORD.getBytes()));
            } else {
                record.setPassword(DigestUtils.md5DigestAsHex(record.getPassword().getBytes()));
            }

            adminMapper.insertSelective(record);
            return JSONResultVO.builder()
                    .code(CODE_SUCCESS)
                    .message("管理员信息添加成功").build();
        } else {
            return JSONResultVO.builder()
                    .code(CODE_SUCCESS)
                    .message("管理员名称重复，请重新输入").build();
        }
    }

    @Override
    @Transactional
    public JSONResultVO delete(Long id) {
        checkNotNull(id, "param id is null");
        AdminDO record = (AdminDO) ((AdminService) AopContext.currentProxy()).get(id).getData();
        if (record.getType().equals(0)) {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message("系统管理员不可以删除自己哦！").build();
        }
        int result = adminMapper.deleteByPrimaryKey(id);
        if (result > 0) {
            return JSONResultVO.builder()
                    .code(CODE_SUCCESS)
                    .message("管理员信息删除成功").build();
        } else {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message("管理员信息删除失败").build();
        }
    }

    @Override
    @Transactional
    public JSONResultVO update(AdminDO record) {
        checkNotNull(record.getId(), "record id is null");
        checkNotNull(record.getName(), "param name is null");
        if (checkNameUnique(record.getName())) {
            if (StringUtils.isBlank(record.getToken())) {
                return JSONResultVO.builder()
                        .code(CODE_ERROR)
                        .message("token is empty").build();
            }
            // 根据token从redis中查询用户信息
            String json = redisTemplate.opsForValue().get(REDIS_USER_SESSION_KEY + SPLIT + record.getToken());
            // 判断是否为空
            if (StringUtils.isBlank(json)) {
                return JSONResultVO.builder()
                        .code(CODE_ERROR)
                        .message("此session已经过期，请重新登录").build();
            }

            if (StringUtils.isNotBlank(record.getPassword())) {
                // 密码加密
                record.setPassword(DigestUtils.md5DigestAsHex(record.getPassword().getBytes()));
            }

            int result = adminMapper.updateByPrimaryKeySelective(record);

            if (result > 0) {
                // 重新生成token
                String token = System.currentTimeMillis() + "" + RandomUtils.nextInt(10000, 99999);
                // 保存用户之前，把用户对象中的密码清空。
                record.setPassword(null);
                // 把用户信息写入redis
                redisTemplate.opsForValue().set(REDIS_USER_SESSION_KEY + SPLIT + token, JsonUtils.objectToJson(record));
                // 设置session的过期时间
                redisTemplate.expire(REDIS_USER_SESSION_KEY + SPLIT + token, expiresIn, TimeUnit.MINUTES);
                return JSONResultVO.builder()
                        .code(CODE_SUCCESS)
                        .data(token)
                        .message("管理员信息修改成功").build();
            } else {
                return JSONResultVO.builder()
                        .code(CODE_ERROR)
                        .data(record.getToken())
                        .message("管理员信息修改失败").build();
            }
        } else {
            return JSONResultVO.builder()
                    .code(CODE_SUCCESS)
                    .message("管理员名称重复，请重新输入").build();
        }
    }

    @Override
    @Transactional
    public JSONResultVO updateCAS(AdminDO record) {
        throw new IllegalAccessError("无法访问的方法");
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO listBy(AdminDO query) {
        AdminDOExample example = new AdminDOExample();
        Criteria criteria = example.createCriteria();
        //TODO edit your query condition
        return JSONResultVO.builder()
                .data(adminMapper.selectByExample(example))
                .code(CODE_SUCCESS)
                .message("管理员信息列表查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO getBy(AdminDO query) {
        AdminDOExample example = new AdminDOExample();
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(query.getName())) {
            criteria.andNameEqualTo(query.getName());
        }
        List<AdminDO> result = adminMapper.selectByExample(example);
        checkState(result.size() < 2, "multy result by query");
        if (result.isEmpty()) {
            JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message("管理员信息查询不到此记录").build();
        }
        AdminDO admin = result.get(0);
        admin.setPassword(null);
        return JSONResultVO.builder()
                .data(admin)
                .code(CODE_SUCCESS)
                .message("管理员信息查询成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO login(AdminDO record) {
        AdminDOExample example = new AdminDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(record.getName());
        List<AdminDO> list = adminMapper.selectByExample(example);
        // 如果没有此用户名
        if (list == null || list.size() == 0) {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message(ERROR_TIP).build();
        }
        AdminDO admin = list.get(0);
        // 比对密码
        if (!DigestUtils.md5DigestAsHex(record.getPassword().getBytes()).equals(admin.getPassword())) {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message(ERROR_TIP).build();
        }
        // 生成token
        String token = System.currentTimeMillis() + "" + RandomUtils.nextInt(10000, 99999);

        // 保存用户之前，把用户对象中的密码清空。
        admin.setPassword(null);
        // 把用户信息写入redis
        redisTemplate.opsForValue().set(REDIS_USER_SESSION_KEY + SPLIT + token, JsonUtils.objectToJson(admin));
        // 设置session的过期时间
        redisTemplate.expire(REDIS_USER_SESSION_KEY + SPLIT + token, expiresIn, TimeUnit.MINUTES);
        return JSONResultVO.builder()
                .data(new BackTokenVO(token, expiresIn))
                .code(CODE_SUCCESS)
                .message("登录成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO logout(String token) {
        if (StringUtils.isBlank(token)) {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message("token is empty").build();
        }
        // 清除Redis记录
        redisTemplate.delete(REDIS_USER_SESSION_KEY + SPLIT + token);
        return JSONResultVO.builder()
                .code(CODE_SUCCESS)
                .message("注销成功").build();
    }

    @Override
    @Transactional(readOnly = true)
    public JSONResultVO info(String token) {
        if (StringUtils.isBlank(token)) {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message("token is empty").build();
        }
        // 根据token从redis中查询用户信息
        String json = redisTemplate.opsForValue().get(REDIS_USER_SESSION_KEY + SPLIT + token);
        if (StringUtils.isBlank(json)) {
            return JSONResultVO.builder()
                    .code(CODE_ERROR)
                    .message("此session已经过期，请重新登录").build();
        }
        // 更新过期时间
        redisTemplate.expire(REDIS_USER_SESSION_KEY + SPLIT + token, 30, TimeUnit.MINUTES);
        return JSONResultVO.builder()
                .data(JsonUtils.jsonToPojo(json, AdminDO.class))
                .code(CODE_SUCCESS)
                .message("获取人员信息成功").build();
    }

    private boolean checkNameUnique(String name) {
        AdminDO record = (AdminDO) ((AdminService) AopContext.currentProxy()).getBy(AdminDO.builder().name(name).build()).getData();
        return Objects.isNull(record);
    }
}

