package com.mountisome.paper_system.dao;

import com.mountisome.paper_system.entity.Admin;
import com.mountisome.paper_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {

    // 通过用户名查询普通用户
    public User findUserByName(String name);

    // 通过用户名查询系统管理员
    public Admin findAdminByName(String name);

    // 查询所有普通用户
    public List<User> findAllUsers();

}
