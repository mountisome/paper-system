package com.mountisome.paper_system.service;

import com.mountisome.paper_system.entity.User;

import java.io.IOException;

public interface RegisterService {

    // 添加用户
    public void addNewUser(User user) throws IOException;

}
