package com.mountisome.paper_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class SubTitle {

    private int id; // 编号
    private String name; // 名称
    private int num; // 搜索次数


}
