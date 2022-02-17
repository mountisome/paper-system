package com.mountisome.paper_system.service.impl;

import com.mountisome.paper_system.dao.UserManageMapper;
import com.mountisome.paper_system.entity.User;
import com.mountisome.paper_system.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserManageMapper userManageMapper;

    public List<User> findAllUsers() {
        return userManageMapper.findAllUsers();
    }

    public void modifyByName(User user) throws IOException {
        userManageMapper.modifyByName(user);
    }

    public void modifyByAdmin(User user) throws IOException {
        userManageMapper.modifyByAdmin(user);
    }

    public List<User> findByUserName(String name) throws IOException {
        return userManageMapper.findByUserName(name);
    }

    public User findUserByName(String name) {
        return userManageMapper.findUserByName(name);
    }

    public User findByUserId(int id) throws IOException {
        return userManageMapper.findByUserId(id);
    }

    public void deleteByUserId(int id) throws IOException {
        userManageMapper.deleteByUserId(id);
    }

}
