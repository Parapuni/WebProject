package cmt.web;

import cmt.db.jdbc.*;
import cmt.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

    @RequestMapping(value = "/change-avatar", method = POST)
    public String changeAvatar(@RequestParam("avatarFile") MultipartFile avatarFile, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        if (avatarFile.isEmpty()) {
            session.setAttribute("error", "Please select a file to upload.");
            return "redirect:/profile";
        }

        try {
            String uploadDir = "F:\\WorkSpace\\WebDevelop\\FinalHomework\\CommentSystem\\src\\main\\resources\\imagines\\";
            String fileName = user.getUid() + "_" + avatarFile.getOriginalFilename(); // 使用用户ID和原文件名
            File destination = new File(uploadDir + fileName);
            avatarFile.transferTo(destination);

            // 更新用户的头像路径
            URL avatarUrl = new URL("http://localhost:8080/imagines/" + fileName);
            user.setAvatar(avatarUrl);
            // 更新数据库记录（假设有 userService）
            userJdbc.updateUser(user);

            // 更新会话中的用户信息
            session.setAttribute("user", user);

        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("error", "Failed to upload avatar.");
            return "redirect:/profile";
        }

        return "redirect:/profile"; // 上传成功后跳转回个人页面
    }
}
