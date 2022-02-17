package com.mountisome.paper_system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mountisome.paper_system.dao.PaperInfoMapper;
import com.mountisome.paper_system.entity.PaperInfo;
import com.mountisome.paper_system.service.PaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PaperInfoServiceImpl implements PaperInfoService {

    @Autowired
    private PaperInfoMapper paperInfoMapper;

    public List<PaperInfo> findAllPapers() throws IOException {
        return paperInfoMapper.findAllPapers();
    }

    public PaperInfo findByPaperInfoId(int id) throws IOException {
        return paperInfoMapper.findByPaperInfoId(id);
    }

    public List<PaperInfo> findByPaperInfo(PaperInfo paperInfo) throws IOException {
        return paperInfoMapper.findByPaperInfo(paperInfo);
    }

    public List<PaperInfo> findByMultiPaperInfo(PaperInfo paperInfo) throws IOException {
        return paperInfoMapper.findByMultiPaperInfo(paperInfo);
    }

    public void addPaperInfo(PaperInfo paperInfo) throws IOException {
        paperInfoMapper.addPaperInfo(paperInfo);
    }

    public void updatePaperDownload(int id) throws IOException {
        paperInfoMapper.updatePaperDownload(id);
    }

    public void updatePaperInfo(PaperInfo paperInfo) throws IOException {
        paperInfoMapper.updatePaperInfo(paperInfo);
    }

    public void deletePaperInfo(int id) throws IOException {
        paperInfoMapper.deletePaperInfo(id);
    }

}
