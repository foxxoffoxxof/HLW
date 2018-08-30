package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


import static com.example.demo.constant.PageConstant.*;

@Controller
public class ControllerLogin {
    public final LoginService loginService;

    @Autowired
    public ControllerLogin(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/tologin1")
    public String index1() {
        return "login1";
    }
    @RequestMapping("/tologin2")
    public String index2() {
        return "login2";
    }
    @RequestMapping("/toregister")
    public String index3() {
        return "register";
    }
    @RequestMapping("/touser1delete")
    public String index4(){return "user1delete";}
    @RequestMapping("/touser1modify")
    public String index5(){return "user1modify";}
    @RequestMapping("/touser1pwd")
    public String index6(){return "user1pwd";}
    @RequestMapping("/touser2")
    public String index7(){return "user2";}
    @RequestMapping("/touser2modify")
    public String index8(String number,HttpSession session){
        session.setAttribute("number",number);
        return "user2modify";
    }


    //主页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    //普通用户主页面
    @RequestMapping("/user1")
    public String result(){
        return "user1";
    }
    //普通用户登录
    @RequestMapping(value = "login1")
    public String login1(User users, HttpServletRequest request, HttpSession session,RedirectAttributes attributes) {
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        users.setNumber(number);
        users.setPassword(password);
        users=this.loginService.login(users);
        session.setAttribute("number",number);
        if (users != null) {
            session.setAttribute("name", users.getName());
            return SUCCESS1;
        } else {
            attributes.addFlashAttribute("mess","用户名或密码错误");

        }
        return FAIL1;
    }
    //管理员登录
    @RequestMapping(value = "login2")
    public String login2(HttpServletRequest request,RedirectAttributes attributes) {
        String username = request.getParameter("number");
        String password = request.getParameter("password");
        String name="fox";
        String pwd="123456";
        if (name.equals(username)&&pwd.equals(password)) {
            return SUCCESS2;
        } else {
            attributes.addFlashAttribute("mess","用户名或密码错误");

        }
        return FAIL2;
    }
    //注册
    @RequestMapping(value = "register")
    public String regiter(User users, RedirectAttributes attributes){
        String number=users.getNumber();
        User user=loginService.register1(number);
        if(null!=user){
            attributes.addFlashAttribute("mess","用户名已被注册");
            return FAIL3;
        }
        else if(number.equals("fox")){
            attributes.addFlashAttribute("mess","用户名已被注册");
            return FAIL3;
        }
        try {
            loginService.register2(users);
            return SUCCESS3;
        }catch (Exception e){
            attributes.addFlashAttribute("mess","日期格式出错");
            return FAIL3;
        }
    }
    //普通用户查询
    @RequestMapping(value = "user1query")
    public String user1query(HttpSession session){
        Object object=session.getAttribute("number");
        String number=(String)object;
        List<User> list=loginService.findByName(number);
        session.setAttribute("lists", list);
        return "user1query";
    }
    //普通用户删除账号
    @RequestMapping(value = "user1delete")
    public String user1delete(User users,HttpSession session,HttpServletRequest request,RedirectAttributes attributes){
        String password = request.getParameter("password");
        Object object=session.getAttribute("number");
        String number=(String)object;
        users.setNumber(number);
        users.setPassword(password);
        users=this.loginService.login(users);
        if (users != null) {
            loginService.delete(number);
            return SUCCESS3;
        } else {
            attributes.addFlashAttribute("mess","密码错误，请重新输入");
        }
        return FAIL4;

    }
    //普通用户修改信息
    @RequestMapping(value = "user1modify")
    public String user1modify(User users,HttpSession session,HttpServletRequest request,RedirectAttributes attributes){
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String date=request.getParameter("date");
        String tel=request.getParameter("tel");
        String password=request.getParameter("password");
        Object object=session.getAttribute("number");
        String number=(String)object;
        users.setNumber(number);
        users.setPassword(password);
        users=this.loginService.login(users);

        System.out.println(name!=null);
        System.out.println(name!="");

        if(users!=null){
            if (name!=null&&name!="")
                loginService.upname(name,number);
            if(sex!=null&&sex!="")
                loginService.upsex(sex,number);
            if(date!=null&&date!="")
                loginService.update(date,number);
            if (tel!=null&&tel!="")
                loginService.uptel(tel,number);
            return SUCCESS3;
        }else {
            attributes.addFlashAttribute("mess","密码错误，修改失败");
        }
        return FAIL5;
    }
    //普通用户修改密码
    @RequestMapping(value = "user1pwd")
    public String user1pwd(User users,HttpSession session,HttpServletRequest request,RedirectAttributes attributes){
        String password1=request.getParameter("password1");
        String password2=request.getParameter("password2");
        String password3=request.getParameter("password3");
        Object object=session.getAttribute("number");
        String number=(String)object;
        users.setNumber(number);
        users.setPassword(password1);
        users=this.loginService.login(users);
        if(users!=null){
            if(password2.equals(password3)){
                loginService.uppwd(password2,number);
                return SUCCESS3;
            }else {
                attributes.addFlashAttribute("mess","两次密码不一致，修改失败");
                return FAIL6;
            }
        }else {
            attributes.addFlashAttribute("mess","密码错误，修改失败");
        }
        return FAIL6;
    }

    //管理员查询
    @RequestMapping(value = "user2query")
    public ModelAndView getAllComponent(com.example.demo.entity.Page<User> page,HttpServletRequest request){
        ModelAndView view=new ModelAndView();
        page = loginService.getAllComponent(page);
        view.addObject("page",page);
        view.setViewName("user2query");
        String number=request.getParameter("number");
        if (number!=null&& !number.equals("")){
            User str=loginService.register1(number);
            if (str!=null){
                List<User> list=loginService.findByName(number);
                view.addObject("lists", list);
                view.setViewName("userselect");
            }else{
                view.setViewName("userselect");
            }
        }
        return view;
    }

    //删除
    @RequestMapping(value = "user2delete")
    public String user2delete(String number){
        loginService.delete(number);
        return "user2delete";
    }

    //修改
    @RequestMapping(value = "user2modify")
    public String user2modify(HttpSession session,HttpServletRequest request){
        String name=request.getParameter("name");
        String sex=request.getParameter("sex");
        String date=request.getParameter("date");
        String tel=request.getParameter("tel");
        String password=request.getParameter("password");
        Object object=session.getAttribute("number");
        String number=(String)object;
        if (name!=null&&name!="")
            loginService.upname(name,number);
        if (password!=null&&password!="")
            loginService.uppwd(password,number);
        if(sex!=null&&sex!="")
            loginService.upsex(sex,number);
        if(date!=null&&date!="")
            loginService.update(date,number);
        if (tel!=null&&tel!="")
            loginService.uptel(tel,number);
        return "user2modify2";
    }

}
