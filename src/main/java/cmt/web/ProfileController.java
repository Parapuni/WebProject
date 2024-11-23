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
import java.sql.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ProfileController {
    @Autowired
    private UserJdbc userJdbc;

    @RequestMapping(value = "/edit-profile", method = GET)
    public String showEditProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "editProfile";
    }

    @RequestMapping(value = "/update-profile", method = POST)
    public String processUpdateProfile(Model model, HttpSession session,
                                       @RequestParam("nickname") String nickname,
                                       @RequestParam("password") String password,
                                       @RequestParam("email") String email,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("birthday")Date birthday,
                                       @RequestParam("number") String phone){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        user.setNickname(nickname);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        user.setNumber(phone);
        userJdbc.updateUser(user);
        model.addAttribute("user", user);
        return "profile";
    }
}
