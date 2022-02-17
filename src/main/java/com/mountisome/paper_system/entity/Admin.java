package com.mountisome.paper_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Admin {

    private int id; // 编号
    private String name; // 用户名
    private String pwd; // 密码
    private String addtime; // 添加时间

    public Admin(String name, String pwd, String addtime) {
        this.name = name;
        this.pwd = pwd;
        this.addtime = addtime;
    }

}
