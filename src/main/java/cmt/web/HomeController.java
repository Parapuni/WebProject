package cmt.web;

import cmt.db.jdbc.UserJdbc;
import cmt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserJdbc userJdbc;

    @RequestMapping(method = GET)
    public String showHomePage(Model model) {
        String welcomeMessage = "Welcome to the Reviews System!";
        model.addAttribute("welcomeMessage", welcomeMessage);

        String platformDescription = "This platform allows you to submit reviews for books, movies, and music.";
        model.addAttribute("platformDescription", platformDescription);

        return "home";
    }

    @RequestMapping(value = "/login", method = GET)
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = POST)
    public String login(@RequestParam String userName, @RequestParam String password, HttpSession session) {
        User user = userJdbc.findUserByNameAndPassword(userName, password);
        if ("admin".equals(userName) && "admin".equals(password)) {
            session.setAttribute("manager", userName);
            return "redirect:/manager";
        }
        return "login";
    }
}
