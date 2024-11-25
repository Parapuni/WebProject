package cmt.web;

import cmt.db.jdbc.AdminJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cmt.db.jdbc.*;
import cmt.entity.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private ItemJdbc itemJdbc;

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
    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public String deleteComment(@RequestParam("iid") long iid,
                                @RequestParam("uid") long uid,
                                HttpSession session) {
        commentJdbc.removeComment(iid,uid);
        session.setAttribute("message","删除成功");
        return "redirect:/admin/manageComments";
    }

    // -----------------------------------
    // 作品管理
    // -----------------------------------

    /**
     * 书本管理页面
     */
    @RequestMapping(value = "/manageBook", method = RequestMethod.GET)
    public String manageItems(@RequestParam(value = "page", defaultValue = "1") int page,
                              Model model) {
        int offset = (page - 1) * PAGE_SIZE;
        List<?> items;
        int totalItems;

        items = bookJdbc.findBooks(offset, PAGE_SIZE);
        totalItems = bookJdbc.countTotal();

        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/bookManage";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String showAddBookPage() {
        return "admin/addBook";
    }

    /**
     * 处理添加作品请求
     */
    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String saveBook(@RequestParam("title") String title,
                           @RequestParam("releaseDate") Date releaseDate,
                           @RequestParam("tags") String tags,
                           @RequestParam("authors") String authors,
                           @RequestParam("publisher") String publisher,
                           @RequestParam("introduction") String introduction,
                           HttpSession session) {
        Book book = new Book();
        book.setTitle(title);
        book.setReleaseDate(releaseDate);
        book.setAuthors(authors);
        book.setPublisher(publisher);
        book.setIntroduction(introduction);

        //处理tags
        String[] tagArray = tags.split(",");
        List<String> tagList = Arrays.asList(tagArray);
        book.setCategories(tagList);

        bookJdbc.addBook(book);
        session.setAttribute("message", "书籍添加成功！");

        return "redirect:/admin/manageBook";
    }

    /**
     * 删除作品
     */
    @RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
    public String deleteBook(@RequestParam("id") Long iid, HttpSession session) {
        bookJdbc.removeBook(iid);
        session.setAttribute("message", "作品删除成功！");
        return "redirect:/admin/manageBook";
    }

    @RequestMapping(value = "/editBook", method = RequestMethod.GET)
    public String editBook(@RequestParam("id") Long iid,
                           HttpSession session) {
        Book book = bookJdbc.findBookById(iid);
        session.setAttribute("book", book);
        return "admin/editBook";
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBook(@RequestParam("iid") Long iid,
                             @RequestParam("title") String title,
                             @RequestParam("releaseDate") Date releaseDate,
                             @RequestParam("tags") String tags,
                             @RequestParam("authors") String authors,
                             @RequestParam("publisher") String publisher,
                             @RequestParam("introduction") String introduction,
                             HttpSession session) {
        Book book = bookJdbc.findBookById(iid);
        System.out.println(Arrays.toString(book.getStars()));

        book.setTitle(title);
        book.setReleaseDate(releaseDate);
        book.setAuthors(authors);
        book.setPublisher(publisher);
        book.setIntroduction(introduction);

        //处理tags
        tags = tags.replace("[", "").replace("]", "").trim();
        String[] tagArray = tags.split(",");
        List<String> tagList = Arrays.asList(tagArray);
        book.setCategories(tagList);

        System.out.println("stars");
        System.out.println(book.getStars().length);
        for (int i = 0; i < book.getStars().length; i ++) {
            System.out.println(book.getStars()[i]);
        }
        bookJdbc.updateBook(book);
        session.setAttribute("message", "书籍信息更新成功！");

        return "redirect:/admin/manageBook";
    }


    @RequestMapping(value = "/changeBookCover", method = RequestMethod.POST)
    public String changeBookCover(@RequestParam("coverImagine") MultipartFile coverImagine,
                                     @RequestParam("id") Long iid, HttpSession session) {

        Book book = bookJdbc.findBookById(iid);
        if (book == null) {
            session.setAttribute("message", "Book not found.");
            return "redirect:/admin/manageBook";
        }

        if (coverImagine.isEmpty()) {
            session.setAttribute("message", "Please select a file to upload.");
            return "redirect:/admin/editBook?id=" + book.getIid();
        }

        try {
            String uploadDir = getClass().getClassLoader().getResource("").getPath()+"imagines/";
            String fileName ="IID"+book.getIid()+coverImagine.getOriginalFilename(); // 使用用户ID和原文件名
            File destination = new File(uploadDir + fileName);
            coverImagine.transferTo(destination);

            // 更新用户的头像路径
            book.setCoverImagine(fileName);
            // 更新数据库记录（假设有 userService）
            bookJdbc.updateBook(book);

            // 更新会话中的用户信息
            session.setAttribute("book", book);
            session.setAttribute("message", "书籍信息更新成功！");

        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("message", "Failed to upload coverImage.");
            return "redirect:/admin/editBook?id=" + book.getIid();
        }

        return "redirect:/admin/manageBook";
    }


    /*
    * 音乐管理部分
    */
    @RequestMapping(value = "/manageMusic", method = RequestMethod.GET)
    public String manageMusic(@RequestParam(value = "page", defaultValue = "1") int page,
                              Model model) {
        int offset = (page - 1) * PAGE_SIZE;
        List<?> items;
        int totalItems;

        items = musicJdbc.findMusics(offset, PAGE_SIZE);
        totalItems = musicJdbc.countTotal();

        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/musicManage";
    }

    @RequestMapping(value = "/addMusic", method = RequestMethod.GET)
    public String showAddMusicPage() {
        return "admin/addMusic";
    }

    @RequestMapping(value = "/saveMusic", method = RequestMethod.POST)
    public String saveMusic(@RequestParam("title") String title,
                            @RequestParam("releaseDate") Date releaseDate,
                            @RequestParam("tags") String tags,
                            @RequestParam("artists") String artists,
                            @RequestParam("album") String album,
                            @RequestParam("lyrics") String lyrics,
                            HttpSession session) {
        Music music = new Music();
        music.setTitle(title);
        music.setReleaseDate(releaseDate);
        music.setArtists(artists);
        music.setAlbum(album);
        music.setLyrics(lyrics);

        //处理tags
        String[] tagArray = tags.split(",");
        List<String> tagList = Arrays.asList(tagArray);
        music.setCategories(tagList);

        musicJdbc.addMusic(music);
        session.setAttribute("message", "音乐添加成功！");

        return "redirect:/admin/manageMusic";
    }

    @RequestMapping(value = "/deleteMusic", method = RequestMethod.GET)
    public String deleteMusic(@RequestParam("id") Long iid, HttpSession session) {
        musicJdbc.removeMusic(iid);
        session.setAttribute("message", "音乐删除成功！");
        return "redirect:/admin/manageMusic";
    }

    @RequestMapping(value = "/editMusic", method = RequestMethod.GET)
    public String editMusic(@RequestParam("id") Long iid,
                            HttpSession session) {
        Music music = musicJdbc.findMusicById(iid);
        session.setAttribute("music", music);
        return "admin/editMusic";
    }

    @RequestMapping(value = "/updateMusic", method = RequestMethod.POST)
    public String updateMusic(@RequestParam("iid") Long iid,
                              @RequestParam("title") String title,
                              @RequestParam("releaseDate") Date releaseDate,
                              @RequestParam("tags") String tags,
                              @RequestParam("artists") String artists,
                              @RequestParam("album") String album,
                              @RequestParam("lyrics") String lyrics,
                              HttpSession session) {
        Music music = musicJdbc.findMusicById(iid);
        music.setTitle(title);
        music.setReleaseDate(releaseDate);
        music.setArtists(artists);
        music.setAlbum(album);
        music.setLyrics(lyrics);

        //处理tags
        tags = tags.replace("[", "").replace("]", "").trim();
        String[] tagArray = tags.split(",");
        List<String> tagList = Arrays.asList(tagArray);
        music.setCategories(tagList);

        musicJdbc.updateMusic(music);
        session.setAttribute("message", "音乐信息更新成功！");

        return "redirect:/admin/manageMusic";
    }

    @RequestMapping(value = "/changeMusicCover", method = RequestMethod.POST)
    public String changeMusicCover(@RequestParam("coverImagine") MultipartFile coverImagine,
                                   @RequestParam("id") Long iid, HttpSession session) {

        Music music = musicJdbc.findMusicById(iid);
        if (music == null) {
            session.setAttribute("message", "Music not found.");
            return "redirect:/admin/manageMusic";
        }

        if (coverImagine.isEmpty()) {
            session.setAttribute("message", "Please select a file to upload.");
            return "redirect:/admin/editMusic?id=" + music.getIid();
        }

        try {
            String uploadDir = getClass().getClassLoader().getResource("").getPath()+"imagines/";
            String fileName ="IID"+music.getIid()+coverImagine.getOriginalFilename(); // 使用用户ID和原文件名
            File destination = new File(uploadDir + fileName);
            coverImagine.transferTo(destination);

            // 更新用户的头像路径
            music.setCoverImagine(fileName);
            // 更新数据库记录（假设有 userService）
            musicJdbc.updateMusic(music);

            // 更新会话中的用户信息
            session.setAttribute("music", music);
            session.setAttribute("message", "音乐信息更新成功！");

        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("message", "Failed to upload coverImage.");
            return "redirect:/admin/editMusic?id=" + music.getIid();
        }

        return "redirect:/admin/manageMusic";
    }


    /*
    * 电影管理部分
     */
    @RequestMapping(value = "/manageMovie", method = RequestMethod.GET)
    public String manageMovie(@RequestParam(value = "page", defaultValue = "1") int page,
                              Model model) {
        int offset = (page - 1) * PAGE_SIZE;
        List<?> items;
        int totalItems;

        items = movieJdbc.findMovies(offset, PAGE_SIZE);
        totalItems = movieJdbc.countTotal();

        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "admin/movieManage";
    }

    @RequestMapping(value = "/addMovie", method = RequestMethod.GET)
    public String showAddMoviePage() {
        return "admin/addMovie";
    }

    @RequestMapping(value = "/saveMovie", method = RequestMethod.POST)
    public String saveMovie(@RequestParam("title") String title,
                            @RequestParam("releaseDate") Date releaseDate,
                            @RequestParam("tags") String tags,
                            @RequestParam("director") String director,
                            @RequestParam("writers") String writers,
                            @RequestParam("cast") String cast,
                            @RequestParam("introduction") String introduction,
                            HttpSession session) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setReleaseDate(releaseDate);
        movie.setDirector(director);
        movie.setWriters(writers);
        movie.setCast(cast);
        movie.setIntroduction(introduction);

        //处理tags
        String[] tagArray = tags.split(",");
        List<String> tagList = Arrays.asList(tagArray);
        movie.setCategories(tagList);

        movieJdbc.addMovie(movie);
        session.setAttribute("message", "电影添加成功！");

        return "redirect:/admin/manageMovie";
    }

    @RequestMapping(value = "/deleteMovie", method = RequestMethod.GET)
    public String deleteMovie(@RequestParam("id") Long iid, HttpSession session) {
        movieJdbc.removeMovie(iid);
        session.setAttribute("message", "电影删除成功！");
        return "redirect:/admin/manageMovie";
    }

    @RequestMapping(value = "/editMovie", method = RequestMethod.GET)
    public String editMovie(@RequestParam("id") Long iid,
                            HttpSession session) {
        Movie movie = movieJdbc.findMovieById(iid);
        session.setAttribute("movie", movie);
        return "admin/editMovie";
    }

    @RequestMapping(value = "/updateMovie", method = RequestMethod.POST)
    public String updateMovie(@RequestParam("iid") Long iid,
                              @RequestParam("title") String title,
                              @RequestParam("releaseDate") Date releaseDate,
                              @RequestParam("tags") String tags,
                              @RequestParam("director") String director,
                              @RequestParam("writers") String writers,
                              @RequestParam("cast") String cast,
                              @RequestParam("introduction") String introduction,
                              HttpSession session) {
        Movie movie = movieJdbc.findMovieById(iid);
        movie.setTitle(title);
        movie.setReleaseDate(releaseDate);
        movie.setDirector(director);
        movie.setWriters(writers);
        movie.setCast(cast);
        movie.setIntroduction(introduction);

        //处理tags
        tags = tags.replace("[", "").replace("]", "").trim();
        String[] tagArray = tags.split(",");
        List<String> tagList = Arrays.asList(tagArray);
        movie.setCategories(tagList);

        movieJdbc.updateMovie(movie);
        session.setAttribute("message", "电影信息更新成功！");

        return "redirect:/admin/manageMovie";
    }

    @RequestMapping(value = "/changeMovieCover", method = RequestMethod.POST)
    public String changeMovieCover(@RequestParam("coverImagine") MultipartFile coverImagine,
                                   @RequestParam("id") Long iid, HttpSession session) {

        Movie movie = movieJdbc.findMovieById(iid);
        if (movie == null) {
            session.setAttribute("message", "Movie not found.");
            return "redirect:/admin/manageMovie";
        }

        if (coverImagine.isEmpty()) {
            session.setAttribute("message", "Please select a file to upload.");
            return "redirect:/admin/editMovie?id=" + movie.getIid();
        }

        try {
            String uploadDir = getClass().getClassLoader().getResource("").getPath()+"imagines/";
            String fileName ="IID"+movie.getIid()+coverImagine.getOriginalFilename(); // 使用用户ID和原文件名
            File destination = new File(uploadDir + fileName);
            coverImagine.transferTo(destination);

            // 更新用户的头像路径
            movie.setCoverImagine(fileName);
            // 更新数据库记录（假设有 userService）
            movieJdbc.updateMovie(movie);

            // 更新会话中的用户信息
            session.setAttribute("movie", movie);
            session.setAttribute("message", "电影信息更新成功！");

        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("message", "Failed to upload coverImage.");
            return "redirect:/admin/editMovie?id=" + movie.getIid();
        }

        return "redirect:/admin/manageMovie";
    }
}
