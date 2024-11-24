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
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ItemController {
    private static final int PAGE_SIZE = 3;
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

    private List<Book> books = new ArrayList<Book>();
    private List<Movie> movies = new ArrayList<Movie>();
    private List<Music> musics = new ArrayList<Music>();
    private List<Comment> comments = new ArrayList<Comment>();
    private Book book = new Book();
    private Movie movie = new Movie();
    private Music music = new Music();

    @RequestMapping(value = "/items", method = GET)
    public String showItemsPage(@RequestParam(value = "category") String category,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                Model model) {
        model.addAttribute("category", category);
        List<String> categories = new ArrayList<String>();
        categories.add("Book");
        categories.add("Movie");
        categories.add("Music");
        model.addAttribute("categories", categories);

        switch (category){
            case "Book":
                books = bookJdbc.findBooks((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", books);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", (int)Math.ceil((double)bookJdbc.countTotal() / PAGE_SIZE));
                break;
            case "Movie":
                movies = movieJdbc.findMovies((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", movies);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", (int)Math.ceil((double)movieJdbc.countTotal() / PAGE_SIZE));
                break;
            case "Music":
                musics = musicJdbc.findMusics((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", musics);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", (int)Math.ceil((double)musicJdbc.countTotal() / PAGE_SIZE));
                break;
            default:
                model.addAttribute("categoryName", "Unknown");
        }

        return "itemsPage";
    }

    @RequestMapping(value = "/item-details", method = GET)
    public String showItemDetials(@RequestParam(value = "id")Integer iid,
                                  @RequestParam(value = "category")String category,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  Model model){
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
            default:
                model.addAttribute("categoryName", "Unknown");
        }
        comments = commentJdbc.findCommentsByItemId(iid, (page - 1) * PAGE_SIZE, PAGE_SIZE);
        model.addAttribute("comments", comments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int)Math.ceil((double)commentJdbc.countTotal() / PAGE_SIZE));

        return "itemReview";
    }
}
