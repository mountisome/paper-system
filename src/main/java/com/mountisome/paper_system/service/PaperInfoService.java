package com.mountisome.paper_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mountisome.paper_system.entity.PaperInfo;

import java.io.IOException;
import java.util.List;

public interface PaperInfoService {

    // 查询所有论文
    public List<PaperInfo> findAllPapers() throws IOException;

    // 通过序号查询论文
    public PaperInfo findByPaperInfoId(int id) throws IOException;

    // 通过标题,作者,摘要,关键字模糊查询论文
    public List<PaperInfo> findByPaperInfo(PaperInfo paperInfo) throws IOException;

    // 复合检索论文
    public List<PaperInfo> findByMultiPaperInfo(PaperInfo paperInfo) throws IOException;

    // 添加论文
    public void addPaperInfo(PaperInfo paperInfo) throws IOException;

    // 更新论文下载次数
    public void updatePaperDownload(int id) throws IOException;

    // 修改论文信息
    public void updatePaperInfo(PaperInfo paperInfo) throws IOException;

    // 删除论文信息
    public void deletePaperInfo(int id) throws IOException;

}
