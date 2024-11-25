package cmt.web;

import cmt.db.jdbc.AdminJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;

import cmt.db.jdbc.*;
import cmt.entity.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
    @RequestMapping(value = "/admindashboard", method = RequestMethod.GET)
    public String adminDashboard() {
        return "admindashboard";
    }

    // -----------------------------------
    // 用户管理
    // -----------------------------------

    /**
     * 用户管理页面
     */
    @RequestMapping(value = "/manageusers", method = RequestMethod.GET)
    public String manageUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size,
                              Model model) {
        int offset = (page - 1) * size;
        List<User> users = userJdbc.findUsers(offset, size);
        int totalUsers = userJdbc.countTotal();
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "usermanage";
    }

    /**
     * 添加用户页面
     */
    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String showAddUserPage() {
        return "adduser";
    }

    /**
     * 处理添加用户请求
     */
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(User user, Model model) {
        userJdbc.addUser(user);
        model.addAttribute("message", "用户添加成功！");
        return "redirect:/admin/manageusers";
    }

    /**
     * 编辑用户页面
     */
    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public String editUser(@RequestParam("uid") Long uid, Model model) {
        User user = userJdbc.findUserById(uid);
        model.addAttribute("user", user);
        return "edituser";
    }

    /**
     * 处理更新用户请求
     */
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public String updateUser(User user, Model model) {
        userJdbc.updateUser(user);
        model.addAttribute("message", "用户信息更新成功！");
        return "redirect:/admin/manageusers";
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("uid") Long uid, Model model) {
        userJdbc.removeUser(uid);
        model.addAttribute("message", "用户删除成功！");
        return "redirect:/admin/manageusers";
    }

    // -----------------------------------
    // 评论管理
    // -----------------------------------

    /**
     * 评论管理页面
     */
    @RequestMapping(value = "/managecomments", method = RequestMethod.GET)
    public String manageComments(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                 Model model) {
        int offset = (page - 1) * size;
        List<Comment> comments = commentJdbc.findCommentsByItemId(0, offset, size); // 修改为实际评论查询
        int totalComments = commentJdbc.countTotal();
        int totalPages = (int) Math.ceil((double) totalComments / size);

        model.addAttribute("comments", comments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "commentmanage";
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
    @RequestMapping(value = "/itemmanage", method = RequestMethod.GET)
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
