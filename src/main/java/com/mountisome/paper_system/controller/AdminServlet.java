package com.mountisome.paper_system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mountisome.paper_system.entity.Admin;
import com.mountisome.paper_system.entity.PaperInfo;
import com.mountisome.paper_system.entity.User;
import com.mountisome.paper_system.service.AdminManageService;
import com.mountisome.paper_system.service.PaperInfoService;
import com.mountisome.paper_system.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminServlet {

    @Autowired
    private AdminManageService adminManageService;

    @Autowired
    private UserManageService userManageService;

    @Autowired
    private PaperInfoService paperInfoService;

    @GetMapping("/addAdmin")
    public String addAdmin() {
        return "/page/addNewAdmin";
    }

    @GetMapping("/addNewAdmin")
    public String addNewAdmin(String name, String pwd) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String addtime = sdf.format(date);
        Admin admin = new Admin(name, pwd, addtime);
        adminManageService.addNewAdmin(admin);
        return "redirect:/admin/findAllAdmins";
    }

    @GetMapping("/findAllAdmins")
    public ModelAndView findAllAdmins(HttpSession session, String currentPage) throws IOException {
        session.setAttribute("function", "adminInfoAdmin");
        if(currentPage == null) {
            currentPage = "1";
        }
        int page = Integer.parseInt(currentPage);
        ModelAndView modelAndView = new ModelAndView();
        PageHelper.startPage(page, 5);
        List<Admin> adminList = adminManageService.findAllAdmins();
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(adminList);
        modelAndView.addObject("adminList", adminList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("adminNum", adminList.size());
        modelAndView.setViewName("/page/manageAdmin");
        return modelAndView;
    }

    @RequestMapping("/findAllUsers")
    public ModelAndView findAllUsers(HttpSession session, String currentPage, String name) throws IOException {
        session.setAttribute("function", "userInfoAdmin");
        if(currentPage == null) {
            currentPage = "1";
        }
        int page = Integer.parseInt(currentPage);
        if(name == null) {
            name = "";
        }
        PageHelper.startPage(page, 5);
        List<User> userList = userManageService.findByUserName(name);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", name);
        modelAndView.addObject("userList", userList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("userNum", userList.size());
        modelAndView.setViewName("/page/manageUser");
        return modelAndView;
    }

    @GetMapping("/findAllPapers")
    public ModelAndView findAllPapers(String currentPage, String subTitle, HttpSession session) throws IOException {
        session.setAttribute("function", "paperInfoAdmin");
        if(currentPage == null) {
            currentPage = "1";
        }
        int page = Integer.parseInt(currentPage);
        if(subTitle == null) {
            subTitle = "";
        }
        PaperInfo paperInfo = new PaperInfo();
        paperInfo.setTitle(subTitle);
        paperInfo.setAuthor(subTitle);
        paperInfo.setAbstracts(subTitle);
        paperInfo.setKeyword(subTitle);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("subTitle", subTitle);
        // 设置分页相关参数  当前页+每页显示的条数
        PageHelper.startPage(page, 5);
        List<PaperInfo> paperInfoList = paperInfoService.findByPaperInfo(paperInfo);
        PageInfo<PaperInfo> pageInfo = new PageInfo<PaperInfo>(paperInfoList);
        modelAndView.addObject("paperInfoList", paperInfoList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("/page/generalSearchByAdmin");
        return modelAndView;
    }

    @GetMapping("/deleteAdmin")
    public String deleteAdmin(String id) throws IOException {
        int adminId = Integer.parseInt(id);
        adminManageService.deleteByAdminId(adminId);
        return "redirect:/admin/findAllAdmins";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(String id) throws IOException {
        int userId = Integer.parseInt(id);
        userManageService.deleteByUserId(userId);
        return "redirect:/admin/findAllUsers";
    }

    @GetMapping("/deletePaper")
    public String deletePaper(String id) throws IOException {
        int paperId = Integer.parseInt(id);
        paperInfoService.deletePaperInfo(paperId);
        return "redirect:/admin/findAllPapers";
    }

    @GetMapping("/updateAdminPwd")
    public ModelAndView updateAdminPwd(HttpSession session) {
        String name = (String) session.getAttribute("loginName");
        String pwd = adminManageService.findPwdByAdminName(name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pwd", pwd);
        modelAndView.setViewName("/page/modifyAdminPwd");
        return modelAndView;
    }

    @PostMapping("/modifyAdminPwd")
    public String modifyAdminPwd(String name, String newPwd) throws IOException {
        Admin admin = new Admin();
        admin.setName(name);
        admin.setPwd(newPwd);
        adminManageService.modifyByAdminName(admin);
        return "redirect:/";
    }

    @PostMapping("/modifyUserPwd")
    public String modifyUserPwd(String id, String pwd) throws IOException {
        int userId = Integer.parseInt(id);
        User user = new User();
        user.setId(userId);
        user.setPwd(pwd);
        userManageService.modifyByAdmin(user);
        return "redirect:/admin/findAllUsers";
    }

    @PostMapping("/updatePaper")
    public String updatePaper(HttpServletRequest request, MultipartFile uploadFile) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String classnum = request.getParameter("classnum");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String abstracts = request.getParameter("abstracts");
        String keyword = request.getParameter("keyword");
        String genre = request.getParameter("genre");
        String type = request.getParameter("type");
        String time = request.getParameter("time");
        int download = Integer.parseInt(request.getParameter("download"));
        String originalFilename = uploadFile.getOriginalFilename();
        String address = "WEB-INF/upload/" + originalFilename;
        PaperInfo paperInfo = new PaperInfo(id, classnum, title, author, abstracts, keyword, genre, type, time,
                download, address);
        paperInfoService.updatePaperInfo(paperInfo);
        return "redirect:/admin/findAllPapers";
    }

    @GetMapping("/findUserById")
    public ModelAndView findUserById(String id) throws IOException {
        int userId = Integer.parseInt(id);
        User user = userManageService.findByUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/page/modifyUserPwd");
        return modelAndView;
    }

    @GetMapping("/findPaperById")
    public ModelAndView findPaperById(String id) throws IOException {
        int paperId = Integer.parseInt(id);
        PaperInfo paper = paperInfoService.findByPaperInfoId(paperId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paper", paper);
        modelAndView.setViewName("/page/modifyPaperInfo");
        return modelAndView;
    }

}
