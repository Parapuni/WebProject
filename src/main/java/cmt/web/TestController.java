package cmt.web;

import cmt.entity.Admin;
import cmt.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {
    @RequestMapping("/simulateUserLogin")
    public String simulateUserLogin(HttpSession session) {
        // 创建一个 User 类的实例，并设置必要的属性
        User mockUser = new User();
        mockUser.setUid(1L);
        mockUser.setPassword("userPassword");
        mockUser.setNickname("testUser");
        mockUser.setEmail("user@example.com");

        session.setAttribute("user", mockUser);
        session.removeAttribute("admin");  // 确保 admin 属性被清除
        return "redirect:/home.jsp";
    }

    @RequestMapping("/simulateAdminLogin")
    public String simulateAdminLogin(HttpSession session) {
        // 创建一个 Admin 类的实例，并设置必要的属性
        Admin mockAdmin = new Admin();
        mockAdmin.setAid(1L);
        mockAdmin.setAdminName("adminUser");
        mockAdmin.setEmail("admin@example.com");
        mockAdmin.setPassword("adminPassword");



        session.setAttribute("admin", mockAdmin);
        session.removeAttribute("user");  // 确保 user 属性被清除
        return "redirect:/home.jsp";
    }

    @RequestMapping("/simulateLogout")
    public String simulateLogout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("admin");
        return "redirect:/home.jsp";
    }
}
