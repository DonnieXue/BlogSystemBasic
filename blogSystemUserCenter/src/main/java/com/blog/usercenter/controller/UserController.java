package com.blog.usercenter.controller;

import com.blog.common.annotion.Log;
import com.blog.common.entity.VipUser;
import com.blog.usercenter.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by BRL on 2018/11/12.
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Log
    @RequestMapping("save")
    public String save(VipUser user) {

        userService.saveData(user);
        return "success";
    }
}
