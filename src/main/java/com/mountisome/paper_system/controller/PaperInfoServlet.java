package com.mountisome.paper_system.controller;

import com.mountisome.paper_system.entity.PaperInfo;
import com.mountisome.paper_system.service.PaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/paperinfo")
public class PaperInfoServlet {

    @Autowired
    private PaperInfoService paperInfoService;

    @RequestMapping("/upload")
    public String upload() {
        return "/page/upload";
    }

    @RequestMapping("/showPaperInfo")
    public ModelAndView showPaperInfo(HttpSession session, String id) throws IOException {
        String search = (String) session.getAttribute("search"); // 获取search属性判断是综合检索还是复合检索
        if (session.getAttribute("loginer").equals("admin")) search = "generalAdmin";
        int paperInfoId = Integer.parseInt(id);
        PaperInfo paper = paperInfoService.findByPaperInfoId(paperInfoId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paper", paper);
        modelAndView.addObject("search", search);
        modelAndView.setViewName("/page/paperInformation");
        return modelAndView;
    }

    @RequestMapping("/uploadPaper")
    public String uploadPaper(HttpServletRequest request, MultipartFile uploadFile) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String addtime = sdf.format(date);
        String classnum = request.getParameter("classnum");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String abstracts = request.getParameter("abstracts");
        String keyword = request.getParameter("keyword");
        String genre = request.getParameter("genre");
        String type = request.getParameter("type");
        String time = request.getParameter("time");
        int download = Integer.parseInt(request.getParameter("download"));
        // 获得上传文件的名称
        String originalFilename = uploadFile.getOriginalFilename();
        String address = "upload/" + originalFilename;
        uploadFile.transferTo(new File("E:/projects/论文检索系统/" + address)); // 在这里修改上传文件的路径
        PaperInfo paper = new PaperInfo(classnum, title, author, abstracts, keyword, genre, type, time, address, addtime, download);
        paperInfoService.addPaperInfo(paper);
        return "/page/upload";
    }

    @RequestMapping("/downloadPaper")
    public void downloadPaper(HttpServletResponse response, String id, String address) {
        try{
            int pid = Integer.parseInt(id);
            String filepath = "E:/projects/论文检索系统/" + address;
            System.out.println(filepath);
            File file = new File(filepath);
            // 如果文件存在
            if (file.exists()) {
                System.out.println("文件存在");
                // 获得文件名，并采用UTF-8编码方式进行编码，以解决中文问题
                String filename1 = file.getName();
                System.out.println("文件名：" + filename1);
                String filename2 = URLEncoder.encode(filename1, "utf-8");
                response.setCharacterEncoding("utf-8");
                // 设置文件的类型
                response.setContentType("application/pdf");
                // 设置HTTP头信息中内容
                response.setHeader("Content-Disposition", "attachment;filename=" + filename2);
                // 设置文件的长度
                int fileLength = (int)file.length();
                response.setContentLength(fileLength);
                // 下载次数加一
                paperInfoService.updatePaperDownload(pid);
                // 如果文件长度大于0
                if(fileLength != 0) {
                    //创建输入流
                    InputStream inStream = new FileInputStream(filepath);
                    byte[] buf = new byte[4096];
                    // 创建输出流
                    ServletOutputStream servletOS = response.getOutputStream();
                    int readLength;
                    // 读取文件内容并写到response的输出流当中
                    while(((readLength = inStream.read(buf)) > 0)){
                        servletOS.write(buf, 0, readLength);
                    }
                    // 关闭输入流
                    inStream.close();
                    // 关闭输出流
                    servletOS.close();
                }
            }
            else {
                System.out.println("文件不存在~！");
                PrintWriter out = response.getWriter();
                out.println("文件 \"" + filepath + "\" 不存在");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
