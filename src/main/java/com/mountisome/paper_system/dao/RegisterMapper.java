package com.mountisome.paper_system.dao;

import com.mountisome.paper_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {

    // 添加用户
    public void addNewUser(User user);

}
