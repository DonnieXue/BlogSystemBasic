package com.blog.usercenter.service;

import com.alibaba.fastjson.JSON;
import com.blog.common.entity.VipUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by BRL on 2018/11/12.
 */
@Service
public class UserService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void saveData(VipUser user) {

        redisTemplate.opsForValue().set(user.getUserName(), JSON.toJSONString(user));
    }
}
