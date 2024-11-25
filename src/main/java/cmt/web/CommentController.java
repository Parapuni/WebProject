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
    @RequestMapping(value = "/editcomment", method = GET)
    public String showSubmitcommentPage(@RequestParam(value = "category") String category,
                                       @RequestParam(value = "id") Integer iid,
                                       Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
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

    @RequestMapping(value = "/submit-comment", method = RequestMethod.POST)
    public String processSubmitcomment(@RequestParam("commentContent") String content,
                                      @RequestParam(value = "id") Integer iid,
                                      @RequestParam(value = "rating", defaultValue = "1") Integer rating,
                                      Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String category = (String) session.getAttribute("category");
        if (user == null) {
            model.addAttribute("error", "You must be logged in to submit a comment.");
            return "redirect:/login";
        }
        if (rating < 1) {
            rating = 1;
        }

        // 检查用户是否已评论该 item
        Comment existingComment = commentJdbc.findComment(iid, user.getUid());
        if (existingComment != null) {
            System.out.println("Comment exists");
            // 提示用户是否覆盖评论
            model.addAttribute("commentExists", true);
            model.addAttribute("existingComment", existingComment);
            model.addAttribute("content", content);
            model.addAttribute("rating", rating);
            model.addAttribute("originalRating", existingComment.getRating());
            model.addAttribute("iid", iid);

            session.setAttribute("category", category);
            return "editReview"; // 回到评论页面
        }

        // 保存新评论
        Comment comment = new Comment();
        comment.setIid(iid);
        comment.setUid(user.getUid());
        comment.setCdate(Date.valueOf(LocalDate.now()));
        comment.setRating(rating);
        comment.setContent(content);
        comment.setUserName(user.getNickname());
        comment.setItemTitle("Item Title Placeholder");

        itemJdbc.updateRating(iid, rating,1);
        commentJdbc.addComment(comment);



        return "redirect:/item-details?id=" + iid + "&category=" + session.getAttribute("category");
    }

    @RequestMapping(value = "/update-comment", method = RequestMethod.POST)
    public String updatecomment(@RequestParam("commentContent") String content,
                               @RequestParam(value = "id") Integer iid,
                               @RequestParam(value = "rating", defaultValue = "1") Integer rating,
                               @RequestParam(value = "originalRating") Integer originalRating,
                               HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        itemJdbc.updateRating(iid, originalRating, -1);
        itemJdbc.updateRating(iid, rating, 1);
        commentJdbc.updateCommentContent(iid, user.getUid(), content, rating);

        return "redirect:/item-details?id=" + iid + "&category=" + session.getAttribute("category");
    }

    @RequestMapping(value = "/delete-comment", method = RequestMethod.POST)
    public String deleteComment(@RequestParam("commentId") Integer iid,
                                HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        itemJdbc.updateRating(iid,commentJdbc.findComment(iid,user.getUid()).getRating(),-1);
        commentJdbc.removeComment(iid, user.getUid());
        return "redirect:/profile";
    }
}
