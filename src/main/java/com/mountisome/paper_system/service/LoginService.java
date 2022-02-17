package com.mountisome.paper_system.service;

import java.io.IOException;

public interface LoginService {

    // 通过用户名查询普通用户
    public int findUserByName(String name, String pwd) throws IOException;

    // 通过用户名查询系统管理员
    public int findAdminByName(String name, String pwd) throws IOException;

}
