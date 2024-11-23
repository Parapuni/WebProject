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
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserJdbc userJdbc;

    @RequestMapping(value = "/update-profile", method = POST)
    public String processUpdateProfile(Model model, HttpSession session,
                                       @RequestParam("username") String username,
                                       @RequestParam("password") String password,
                                       @RequestParam("email") String email) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        user.setNickname(username);
        user.setPassword(password);
        user.setEmail(email);
        System.out.println(user.getEmail()+"   "+user.getPassword());
        userJdbc.updateUser(user);
        model.addAttribute("user", user);
        return "profile";
    }
}
