package com.mountisome.paper_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaperInfo {

    private int id; // 编号
    private String classnum; // 分类号
    private String title; // 标题
    private String author; // 作者
    private String abstracts; // 摘要
    private String keyword; // 关键字
    private String genre; // 出处
    private String type; // 论文类别
    private String time; // 发表时间
    private int download; // 下载次数
    private String address; // 所在位置
    private String addtime; // 添加时间

    public PaperInfo(int id, String classnum, String title, String author, String abstracts, String keyword,
                     String genre, String type, String time, int download, String address) {
        this.id = id;
        this.classnum = classnum;
        this.title = title;
        this.author = author;
        this.abstracts = abstracts;
        this.keyword = keyword;
        this.genre = genre;
        this.type = type;
        this.time = time;
        this.download = download;
        this.address = address;
    }

    public PaperInfo(String classnum, String title, String author, String abstracts, String keyword, String genre,
                     String type, String time, String address, String addtime, int download) {
        this.classnum = classnum;
        this.title = title;
        this.author = author;
        this.abstracts = abstracts;
        this.keyword = keyword;
        this.genre = genre;
        this.type = type;
        this.time = time;
        this.address = address;
        this.addtime = addtime;
        this.download = download;
    }

}
