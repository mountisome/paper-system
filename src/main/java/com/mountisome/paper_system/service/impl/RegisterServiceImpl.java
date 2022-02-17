package com.mountisome.paper_system.service.impl;

import com.mountisome.paper_system.dao.RegisterMapper;
import com.mountisome.paper_system.entity.User;
import com.mountisome.paper_system.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    public void addNewUser(User user) throws IOException {
        registerMapper.addNewUser(user);
    }

}
