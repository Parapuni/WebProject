package cmt.web;

import cmt.db.jdbc.*;
import cmt.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private ItemJdbc itemJdbc;
    @RequestMapping(method = GET)
    public String showHomePage(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model) {
        /*
        cookie记忆登陆状态
        若已登陆过，记录"hasLogin"，session结束前取消自动登录
         */
        if(session.getAttribute("user") == null && session.getAttribute("hasLogin")==null) {
            Cookie[] cookies = request.getCookies();
            String[] userInfo = null;
            if (cookies != null)
                for (Cookie cookie : cookies) {
                    if ("userinfo".equals(cookie.getName()))
                        userInfo = cookie.getValue().split("_");
                }
            if (userInfo != null) {
                User user = userJdbc.findUserById(Long.parseLong(userInfo[0]));
                session.setAttribute("user", user);
                session.setAttribute("hasLogin",true);
            }
        }

        List<SimpleItem> ten = itemJdbc.getRecommendedItems();
        model.addAttribute("ten",ten);
        List<Book> recentBooks = bookJdbc.findBooks(0,4);
        model.addAttribute("recentBooks",recentBooks);
        List<Movie> recentMovies = movieJdbc.findMovies(0,4);
        model.addAttribute("recentMovies",recentMovies);
        List<Music> recentMusics = musicJdbc.findMusics(0,4);
        model.addAttribute("recentMusics",recentMusics);

        String welcomeMessage = "Welcome to the comments System!";
        model.addAttribute("welcomeMessage", welcomeMessage);

        String platformDescription = "This platform allows you to submit comments for books, movies, and music.";
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
        List<Comment> comments = commentJdbc.findCommentsByUserId(user.getUid(),0, 99999);
        model.addAttribute("comments", comments);
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

    @RequestMapping(value = "/submit-comment", method = GET)
    public String showSubmitcommentPage(Model model) {
        return "submit-comment";
    }



    @RequestMapping(value = "/login", method = POST)
    public String processLogin(@RequestParam(value = "username", defaultValue = "") String userName,
                               @RequestParam(value = "password", defaultValue = "") String password,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               HttpSession session, Model model) {
        User user = userJdbc.findUserByNameAndPassword(userName, password);
        if (user != null) {
            /*
            设置cookie，寿命1天
             */
            Cookie cookie = new Cookie("userinfo",Long.toString(user.getUid())+"_"+user.getNickname());
            cookie.setMaxAge(3600*24);
            cookie.setPath(request.getContextPath());
            System.out.println(cookie.getPath());
            response.addCookie(cookie);
            session.setAttribute("user", user);
            session.setAttribute("hasLogin",true);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegister(@RequestParam(value = "nickname", defaultValue = "") String nickname,
                                  @RequestParam(value = "password", defaultValue = "") String password,
                                  @RequestParam(value = "email", defaultValue = "") String email,
                                  @RequestParam(value = "confirmPassword", defaultValue = "") String passwordConfirm,
                                  HttpSession session) throws MalformedURLException {
        if (userJdbc.isExists(nickname)){
            session.setAttribute("rError","用户名已存在");
            return "register";
        }
        if (!password.equals(passwordConfirm)) {
            session.setAttribute("rError","两次密码不同");
            return "register";
        }
        User user = new User(nickname, password, email);
        userJdbc.addUser(user);
        return "redirect:/profile";
    }


}
