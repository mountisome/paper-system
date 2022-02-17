package com.mountisome.paper_system.dao;

import com.mountisome.paper_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserManageMapper {

    // 查询所有的系统管理员
    public List<User> findAllUsers();

    // 修改用户信息
    public void modifyByName(User user);

    // 管理员修改用户信息
    public void modifyByAdmin(User user);

    // 通过用户名查询普通用户
    public List<User> findByUserName(String name);

    // 通过用户名查询特定用户
    public User findUserByName(String name);

    // 通过序号查询普通用户
    public User findByUserId(int id);

    // 通过序号删除普通用户
    public void deleteByUserId(int id);

}
