package com.mountisome.paper_system.service.impl;

import com.mountisome.paper_system.dao.AdminManageMapper;
import com.mountisome.paper_system.entity.Admin;
import com.mountisome.paper_system.service.AdminManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AdminManageServiceImpl implements AdminManageService {

    @Autowired
    private AdminManageMapper adminManageMapper;

    public List<Admin> findAllAdmins() {
        return adminManageMapper.findAllAdmins();
    }

    public List<Admin> findByAdminName(String name) throws IOException {
        return adminManageMapper.findByAdminName(name);
    }

    public String findPwdByAdminName(String name) {
        return adminManageMapper.findPwdByAdminName(name);
    }

    public void deleteByAdminId(int id) throws IOException {
        adminManageMapper.deleteByAdminId(id);
    }

    public void addNewAdmin(Admin admin) throws IOException {
        adminManageMapper.addNewAdmin(admin);
    }

    public void modifyByAdminName(Admin admin) throws IOException {
        adminManageMapper.modifyByAdminName(admin);
    }

}
