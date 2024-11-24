package cmt.web;

import cmt.db.jdbc.*;
import cmt.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.net.MalformedURLException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserJdbc userJdbc;
    @Autowired
    private AdminJdbc adminJdbc;
    @Autowired
    private CommentJdbc commentJdbc;
    @Autowired
    private BookJdbc bookJdbc;
    @Autowired
    private MovieJdbc movieJdbc;
    @Autowired
    private MusicJdbc musicJdbc;

    @RequestMapping(method = GET)
    public String showHomePage(Model model) {
        String welcomeMessage = "Welcome to the Reviews System!";
        model.addAttribute("welcomeMessage", welcomeMessage);

        String platformDescription = "This platform allows you to submit reviews for books, movies, and music.";
        model.addAttribute("platformDescription", platformDescription);

        return "home";
    }

    @RequestMapping(value = "/profile", method = GET)
    public String showProfilePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/home", method = GET)
    public String showHomePage() {
        return "home";
    }

    @RequestMapping(value = "/login", method = GET)
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegisterForm() {
        return "register";
    }

    @RequestMapping(value = "/reviews", method = GET)
    public String showReviewsPage(Model model) {
        // TODO: 2024/11/20 Add reviews to model
        return "reviews";
    }

    @RequestMapping(value = "/submit-review", method = GET)
    public String showSubmitReviewPage(Model model) {
        return "submit-review";
    }



    @RequestMapping(value = "/login", method = POST)
    public String processLogin(@RequestParam(value = "username", defaultValue = "") String userName,
                               @RequestParam(value = "password", defaultValue = "") String password,
                               HttpSession session) {
        User user = userJdbc.findUserByNameAndPassword(userName, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegister(@RequestParam(value = "username", defaultValue = "") String userName,
                                  @RequestParam(value = "password", defaultValue = "") String password,
                                  @RequestParam(value = "email", defaultValue = "") String email,
                                  @RequestParam(value = "confirmPassword", defaultValue = "") String passwordConfirm,
                                  HttpSession session) throws MalformedURLException {
        if (!password.equals(passwordConfirm)) {
            return "register";
        } else {
            User user = new User(userName, password, email);
            userJdbc.addUser(user);
            return "redirect:profile";
        }
    }
}
