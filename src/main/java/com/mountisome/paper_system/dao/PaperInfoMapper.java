package com.mountisome.paper_system.dao;

import com.mountisome.paper_system.entity.PaperInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperInfoMapper {

    // 查询所有论文
    public List<PaperInfo> findAllPapers();

    // 通过id查询论文
    public PaperInfo findByPaperInfoId(int id);

    // 通过标题,作者,摘要,关键字模糊查询论文，按发表时间降序排列
    public List<PaperInfo> findByPaperInfo(PaperInfo paperInfo);

    // 通过标题,作者,摘要,关键字模糊查询论文，按下载次数降序排列
    public List<PaperInfo> findByPaperInfoDownload(PaperInfo paperInfo);

    // 复合检索论文，按发表时间降序排列
    public List<PaperInfo> findByMultiPaperInfo(PaperInfo paperInfo);

    // 复合检索论文，按下载次数降序排列
    public List<PaperInfo> findByMultiPaperInfoDownload(PaperInfo paperInfo);

    // 添加论文
    public void addPaperInfo(PaperInfo paperInfo);

    // 更新论文下载次数
    public void updatePaperDownload(int id);

    // 修改论文信息
    public void updatePaperInfo(PaperInfo paperInfo);

    // 删除论文信息
    public void deletePaperInfo(int id);

    // 通过名称查询标题
    public List<String> findSubtitlesByName(String name);

}
