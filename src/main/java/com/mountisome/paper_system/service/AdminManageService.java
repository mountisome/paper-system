package com.mountisome.paper_system.service;

import com.mountisome.paper_system.entity.Admin;

import java.io.IOException;
import java.util.List;

public interface AdminManageService {

    // 查询所有的系统管理员
    public List<Admin> findAllAdmins();

    // 通过用户名查询系统管理员
    public List<Admin> findByAdminName(String name) throws IOException;

    // 通过用户名查询系统管理员的密码
    public String findPwdByAdminName(String name);

    // 通过序号删除系统管理员
    public void deleteByAdminId(int id) throws IOException;

    // 添加系统管理员
    public void addNewAdmin(Admin admin) throws IOException;

    // 修改系统管理员的密码
    public void modifyByAdminName(Admin admin) throws IOException;

}
