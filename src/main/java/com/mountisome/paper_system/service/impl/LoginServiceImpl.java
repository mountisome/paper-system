package com.mountisome.paper_system.service.impl;

import com.mountisome.paper_system.dao.LoginMapper;
import com.mountisome.paper_system.entity.Admin;
import com.mountisome.paper_system.entity.User;
import com.mountisome.paper_system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public int findUserByName(String name, String pwd) throws IOException {
        int result = 2; // 普通用户不存在
        User userFind = loginMapper.findUserByName(name);
        if(userFind != null) {
            result = 1; // 普通用户存在
            if(userFind.getPwd().equals(pwd)) {
                result = 0; // 普通用户存在并且密码正确
            }
        }
        return result;
    }

    public int findAdminByName(String name, String pwd) throws IOException {
        int result = 2; // 系统管理员不存在
        Admin admin = loginMapper.findAdminByName(name);
        if(admin != null) {
            result = 1; // 系统管理员存在
            if(admin.getPwd().equals(pwd)) {
                result = 0; // 系统管理员存在并且密码正确
            }
        }
        return result;
    }

}
