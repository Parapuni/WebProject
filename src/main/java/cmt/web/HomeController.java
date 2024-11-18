package cmt.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cmt.db.jdbc.UserJdbc;
import cmt.entity.User;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserJdbc userJdbc;

    @RequestMapping(method = GET)
    public String home(Model model) {
        return "home";
    }

    @RequestMapping(value = "/login", method = GET)
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestParam String userName, @RequestParam String password, HttpSession session) {
        User user = userJdbc.findUserByName(userName, password);
        if ("admin".equals(userName) && "admin".equals(password)) {
            session.setAttribute("manager", userName);
            return "redirect:/manager";
        }
        return "login";
    }
}
