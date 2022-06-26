package com.mountisome.paper_system.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface LoginService {

    // 通过用户名查询普通用户
    public int findUserByName(String name, String pwd) throws IOException, NoSuchAlgorithmException;

    // 通过用户名查询系统管理员
    public int findAdminByName(String name, String pwd) throws IOException, NoSuchAlgorithmException;

}
