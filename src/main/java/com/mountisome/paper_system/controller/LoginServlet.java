package com.mountisome.paper_system.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.mountisome.paper_system.entity.User;
import com.mountisome.paper_system.service.LoginService;
import com.mountisome.paper_system.service.RegisterService;
import com.mountisome.paper_system.service.UserManageService;
import com.mountisome.paper_system.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class LoginServlet {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserManageService userManageService;

    @GetMapping("/")
    public String loginPage(HttpSession session) {
        session.setAttribute("errorCode", "0");
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String checkcode = request.getParameter("checkcode");
        String loginer = request.getParameter("loginer");
        String code = (String) session.getAttribute("checkCode");
        int result = 3; // 验证码不正确
        if(code.equalsIgnoreCase(checkcode)) {
            if (loginer.equals("user")) {
                result = loginService.findUserByName(name, pwd);
                if (result == 0) {
                    session.setAttribute("loginName", name);
                    session.setAttribute("loginer", loginer);
                    session.setAttribute("function", "paperInfoUser");
                    return "redirect:/user/findAllPapers";
                }
                else {
                    session.setAttribute("errorCode", String.valueOf(result));
                    return "login";
                }
            }
            else {
                result = loginService.findAdminByName(name, pwd);
                if (result == 0) {
                    session.setAttribute("loginName", name);
                    session.setAttribute("loginer", loginer);
                    session.setAttribute("function", "paperInfoAdmin");
                    return "redirect:/admin/findAllPapers";
                }
                else {
                    session.setAttribute("errorCode", String.valueOf(result));
                    return "login";
                }
            }
        }
        else {
            session.setAttribute("errorCode", String.valueOf(result));
            return "login";
        }
    }

    @RequestMapping("/registerPage")
    public ModelAndView registerPage() {
        List<User> userList = userManageService.findAllUsers();
        List<String> nameList = new LinkedList<>();
        for (User user : userList) {
            nameList.add(user.getName());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("nameList", nameList);
        modelAndView.setViewName("/page/register");
        return modelAndView;
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String phone = request.getParameter("phone");
        String mailbox = request.getParameter("mailbox");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String addtime = sdf.format(date);
        User user = new User(name, pwd, phone, mailbox, addtime);
        registerService.addNewUser(user);
        return "redirect:/";
    }

    @RequestMapping("/checkcode")
    public void checkcode(HttpSession session, HttpServletResponse response) throws IOException {
        BufferedImage image = ValidateImageCodeUtils.createImage(session);
        //响应图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpeg", os);
    }

}
