package cmt.web;

import cmt.db.jdbc.*;
import cmt.entity.Book;
import cmt.entity.Movie;
import cmt.entity.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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
        return "editReview";
    }

    @RequestMapping(value = "/submit-review", method = RequestMethod.POST)
    public String processSubmitReview(@RequestParam(value = "category") String category,
                                      @RequestParam(value = "id") Integer iid,
                                      @RequestParam(value = "rating") Integer rating,
                                      @RequestParam(value = "comment") String comment,
                                      Model model) {
        switch (category){

        }

        return "itemReview";
    }
}
