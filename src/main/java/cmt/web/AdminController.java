package cmt.web;

import cmt.db.jdbc.AdminJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.net.MalformedURLException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import cmt.db.jdbc.*;
import cmt.entity.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final int PAGE_SIZE = 3;
    @Autowired
    private AdminJdbc adminJdbc;
    @Autowired
    private UserJdbc userJdbc;
    @Autowired
    private CommentJdbc commentJdbc;
    @Autowired
    private BookJdbc bookJdbc;
    @Autowired
    private MovieJdbc movieJdbc;
    @Autowired
    private MusicJdbc musicJdbc;

    /**
     * 管理员仪表盘
     */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showAdminDashboard() {
        return "admin/adminDashboard";
    }



    // -----------------------------------
    // 用户管理
    // -----------------------------------

    /**
     * 用户管理页面
     */
    @RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
    public String manageUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                              Model model) {

        List<User> users = userJdbc.findUsers((page - 1) * PAGE_SIZE, PAGE_SIZE);

        int totalUsers = userJdbc.countTotal();
        int totalPages = (int) Math.ceil((double) totalUsers / PAGE_SIZE);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/userManage";
    }

    /**
     * 添加用户页面
     */
    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String showAddUserPage() {
        return "admin/addUser";
    }

    /**
     * 处理添加用户请求
     */
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@RequestParam("nickname") String nickname,
                           @RequestParam("email")  String email,
                           @RequestParam("number") String number,
                           @RequestParam("password") String password,
                           @RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("birthday") Date birthday,
                           HttpSession session) throws MalformedURLException {
        if (userJdbc.isExists(nickname)) {
            session.setAttribute("message", "用户名已存在！");
            return "redirect:/admin/adduser";
        }
        User user = new User(nickname, password, email);
        user.setNumber(number);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday);
        userJdbc.addUser(user);
        session.setAttribute("message", "用户添加成功！");
        return "redirect:/admin/manageUsers";
    }

    /**
     * 编辑用户页面
     */
    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public String editUser(@RequestParam("uid") Long uid, Model model) {
        User user = userJdbc.findUserById(uid);
        model.addAttribute("user", user);
        return "admin/editUser";
    }

    /**
     * 处理更新用户请求
     */
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String updateUser(@RequestParam("uid") Long uid,
                             @RequestParam("nickname") String nickname,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email")  String email,
                             @RequestParam("number") String number,
                             @RequestParam("birthday") Date birthday,
                             Model model, HttpSession session) {
        User user = userJdbc.findUserById(uid);
        if (userJdbc.isExists(nickname) && !user.getNickname().equals(nickname)) {
            session.setAttribute("message", "用户名已存在！");
            return "redirect:/admin/edituser?uid=" + uid;
        }
        user.setNickname(nickname);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setNumber(number);
        user.setBirthday(birthday);
        userJdbc.updateUser(user);
        session.setAttribute("message", "用户信息更新成功！");

        return "redirect:/admin/manageUsers";
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("uid") Long uid, HttpSession session) {
        userJdbc.removeUser(uid);
        session.setAttribute("message", "用户删除成功！");
        return "redirect:/admin/manageUsers";
    }

    // -----------------------------------
    // 评论管理
    // -----------------------------------

    /**
     * 评论管理页面
     */
    @RequestMapping(value = "/manageComments", method = RequestMethod.GET)
    public String manageComments(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                 Model model) {
        int offset = (page - 1) * size;
        List<Comment> comments = commentJdbc.findAll(PAGE_SIZE, (page - 1) * PAGE_SIZE);
        int totalComments = commentJdbc.countTotal();
        int totalPages = (int) Math.ceil((double) totalComments / size);

        model.addAttribute("comments", comments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/commentManage";
    }

    /**
     * 删除评论
     */
    @RequestMapping(value = "/deletecomment", method = RequestMethod.GET)
    public String deleteComment(@RequestParam("iid") Long iid,
                                @RequestParam("uid") Long uid,
                                Model model) {
        commentJdbc.removeComment(iid, uid);
        model.addAttribute("message", "评论删除成功！");
        return "redirect:/admin/managecomments";
    }

    // -----------------------------------
    // 作品管理
    // -----------------------------------

    /**
     * 作品管理页面
     */
    @RequestMapping(value = "/manageItem", method = RequestMethod.GET)
    public String manageItems(@RequestParam(value = "category", defaultValue = "All") String category,
                              @RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size,
                              Model model) {
        int offset = (page - 1) * size;

        List<?> items;
        int totalItems;

        switch (category) {
            case "Book":
                items = bookJdbc.findBooks(offset, size);
                totalItems = bookJdbc.countTotal();
                break;
            case "Movie":
                items = movieJdbc.findMovies(offset, size);
                totalItems = movieJdbc.countTotal();
                break;
            case "Music":
                items = musicJdbc.findMusics(offset, size);
                totalItems = musicJdbc.countTotal();
                break;
            default:
                items = Collections.emptyList();
                totalItems = 0;
                break;
        }

        int totalPages = (int) Math.ceil((double) totalItems / size);

        model.addAttribute("items", items);
        model.addAttribute("category", category);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "itemmanage";
    }

    /**
     * 添加作品页面
     */
    @RequestMapping(value = "/additem", method = RequestMethod.GET)
    public String showAddItemPage() {
        return "additem";
    }

    /**
     * 处理添加作品请求
     */
    @RequestMapping(value = "/saveitem", method = RequestMethod.POST)
    public String saveItem(@ModelAttribute Book book, Model model) {
        bookJdbc.addBook(book);
        model.addAttribute("message", "作品添加成功！");
        return "redirect:/admin/itemmanage?category=Book";
    }

    /**
     * 删除作品
     */
    @RequestMapping(value = "/deleteitem", method = RequestMethod.GET)
    public String deleteItem(@RequestParam("iid") Long iid,
                             @RequestParam("category") String category,
                             Model model) {
        switch (category) {
            case "Book":
                bookJdbc.removeBook(iid);
                break;
            case "Movie":
                movieJdbc.removeMovie(iid);
                break;
            case "Music":
                musicJdbc.removeMusic(iid);
                break;
        }
        model.addAttribute("message", "作品删除成功！");
        return "redirect:/admin/itemmanage?category=" + category;
    }
}
