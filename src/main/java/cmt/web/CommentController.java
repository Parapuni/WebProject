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

import java.sql.Date;
import java.time.LocalDate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class CommentController {

    @Autowired
    private ItemJdbc itemJdbc;
    @Autowired
    private BookJdbc bookJdbc;
    @Autowired
    private MovieJdbc movieJdbc;
    @Autowired
    private MusicJdbc musicJdbc;
    @Autowired
    private CommentJdbc commentJdbc;
    private Book book = new Book();
    private Movie movie = new Movie();
    private Music music = new Music();
    @RequestMapping(value = "/editreview", method = GET)
    public String showSubmitReviewPage(@RequestParam(value = "category") String category,
                                       @RequestParam(value = "id") Integer iid,
                                       Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        switch (category){
            case "Book":
                book = bookJdbc.findBookById(iid);
                model.addAttribute("item", book);
                break;
            case "Movie":
                movie = movieJdbc.findMovieById(iid);
                model.addAttribute("item", movie);
                break;
            case "Music":
                music = musicJdbc.findMusicById(iid);
                model.addAttribute("item", music);
                break;
        }
        session.setAttribute("category", category);
        return "editReview";
    }

    @RequestMapping(value = "/submit-review", method = RequestMethod.POST)
    public String processSubmitReview(@RequestParam("reviewContent") String content,
                                      @RequestParam(value = "id") Integer iid,
                                      @RequestParam(value = "rating") Integer rating,
                                      Model model, HttpSession session) {
        // 获取当前登录用户
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "You must be logged in to submit a review.");
            return "redirect:/login";
        }

        // 创建 Comment 实例
        Comment comment = new Comment();
        comment.setIid(iid);
        comment.setUid(user.getUid());
        comment.setCdate(Date.valueOf(LocalDate.now()));
        comment.setRating(rating);
        comment.setContent(content);
        comment.setUserName(user.getNickname());
        comment.setItemTitle("Item Title Placeholder");

        if(commentJdbc.findComment(iid, user.getUid()) != null){
            model.addAttribute("error", "You have already submitted a review for this item.");
            return "redirect:/item-details?id=" + iid + "&category=" + session.getAttribute("category");
        }

        System.out.println("Rating: " + rating);
        System.out.println("Content: " + content);
        System.out.println("Item ID: " + iid);

        // 将评论保存到数据库
        commentJdbc.addComment(comment);

        // 重定向到该作品详情页
        return "redirect:/item-details?id=" + iid + "&category=" + session.getAttribute("category");
    }
}
