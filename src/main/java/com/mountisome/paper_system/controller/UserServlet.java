package com.mountisome.paper_system.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mountisome.paper_system.entity.PaperInfo;
import com.mountisome.paper_system.entity.User;
import com.mountisome.paper_system.service.PaperInfoService;
import com.mountisome.paper_system.service.UserManageService;
import com.mountisome.paper_system.utils.SHAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserServlet {

    @Autowired
    private PaperInfoService paperInfoService;

    @Autowired
    private UserManageService userManageService;

    @RequestMapping("/findAllPapers")
    public ModelAndView findAllPapers(HttpSession session, String currentPage, String subTitle,
                                      String order) throws IOException {
        session.setAttribute("function", "paperInfoUser");
        session.setAttribute("search", "general");
        if (order == null) order = "time";
        if (subTitle == null) subTitle = "null";
        int page = 1;
        if (currentPage != null) page = Integer.parseInt(currentPage);
        PaperInfo paperInfo = new PaperInfo();
        paperInfo.setTitle(subTitle);
        paperInfo.setAuthor(subTitle);
        paperInfo.setKeyword(subTitle);
        // 设置分页相关参数  当前页+每页显示的条数
        PageHelper.startPage(page, 5);
        List<PaperInfo> paperInfoList;
        if (order.equals("time")) paperInfoList = paperInfoService.findByPaperInfo(paperInfo);
        else paperInfoList = paperInfoService.findByPaperInfoDownload(paperInfo);
        PageInfo<PaperInfo> pageInfo = new PageInfo<PaperInfo>(paperInfoList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("subTitle", subTitle);
        modelAndView.addObject("paperInfoList", paperInfoList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("/page/managePaperGeneralSearch");
        return modelAndView;
    }

    @RequestMapping("/findAllPapersMulti")
    public ModelAndView findAllPapersMulti(HttpServletRequest request, HttpSession session) throws IOException {
        session.setAttribute("search", "advanced");
        String cPage = request.getParameter("currentPage");
        int currentPage = 1;
        if (cPage != null) currentPage = Integer.parseInt(cPage);
        String classnum = request.getParameter("classnum");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String keyword = request.getParameter("keyword");
        String type = request.getParameter("type");
        String order = request.getParameter("order");
        if (classnum == null) {
            classnum = "";
        }
        if (title == null) {
            title = "null";
        }
        if (author == null) {
            author = "";
        }
        if (keyword == null) {
            keyword = "";
        }
        if (type == null || type.equals("不限")) {
            type = "";
        }
        if (order == null) order = "time";
        PageHelper.startPage(currentPage, 5);
        PaperInfo paperInfo = new PaperInfo();
        paperInfo.setClassnum(classnum);
        paperInfo.setTitle(title);
        paperInfo.setAuthor(author);
        paperInfo.setKeyword(keyword);
        paperInfo.setType(type);
        List<PaperInfo> paperInfoList;

        if (order.equals("time")) paperInfoList = paperInfoService.findByMultiPaperInfo(paperInfo);
        else paperInfoList = paperInfoService.findByMultiPaperInfoDownload(paperInfo);
        PageInfo<PaperInfo> pageInfo = new PageInfo<PaperInfo>(paperInfoList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("paperInfoList", paperInfoList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("classnum", classnum);
        modelAndView.addObject("title", title);
        modelAndView.addObject("author", author);
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("type", type);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("/page/managePaperAdvancedSearch");
        return modelAndView;
    }

    @RequestMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session) {
        session.setAttribute("function", "personInfoUser");
        String name = (String) session.getAttribute("loginName");
        User user = userManageService.findUserByName(name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pwd", user.getPwd());
        modelAndView.addObject("phone", user.getPhone());
        modelAndView.addObject("mailbox", user.getMailbox());
        modelAndView.setViewName("/page/userInformation");
        return modelAndView;
    }

    @RequestMapping("/modifyUser")
    public String modifyUser(HttpServletRequest request, HttpSession session) throws IOException, NoSuchAlgorithmException {
        String name = (String) session.getAttribute("loginName");
        String pwd = request.getParameter("newPwd");
        pwd = SHAUtils.getSHA(name + pwd);
        String phone = request.getParameter("phone");
        String mailbox = request.getParameter("mailbox");
        User user = new User(name, pwd, phone, mailbox);
        userManageService.modifyByName(user);
        return "redirect:/";
    }

    @RequestMapping("/networkPage")
    public String networkPage() {
        return "/page/networkSearch";
    }

    @RequestMapping("/networkSearch")
    public String networkSearch(String keyword, String page) {
        Process process;
        try {
            String[] args = new String[] {"python", "E:\\projects\\论文检索系统\\获取知网作者摘要.py", keyword, page};
            process = Runtime.getRuntime().exec(args);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/user/findAllPapers";
    }

    @RequestMapping("/findSubtitlesByName")
    @ResponseBody
    public String findSubtitlesByName(@RequestParam("subTitle") String subTitle) {
        List<String> titleList;
        titleList = paperInfoService.findSubtitlesByName(subTitle);
        return JSONUtils.toJSONString(titleList);
    }

}
