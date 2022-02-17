package com.mountisome.paper_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class User {

    private int id; // 编号
    private String name; // 用户名
    private String pwd; // 密码
    private String phone; // 电话号码
    private String mailbox; // 邮箱
    private String addtime; // 添加时间

    public User(String name, String pwd, String phone, String mailbox, String addtime) {
        this.name = name;
        this.pwd = pwd;
        this.phone = phone;
        this.mailbox = mailbox;
        this.addtime = addtime;
    }

    public User(String name, String pwd, String phone, String mailbox) {
        this.name = name;
        this.pwd = pwd;
        this.phone = phone;
        this.mailbox = mailbox;
    }

}
