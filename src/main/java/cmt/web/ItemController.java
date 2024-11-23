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

    private List<Book> books = new ArrayList<Book>();
    private List<Movie> movies = new ArrayList<Movie>();
    private List<Music> musics = new ArrayList<Music>();

    @RequestMapping(value = "/items", method = GET)
    public String showItemsPage(@RequestParam(value = "category") String category,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                Model model) {
        model.addAttribute("category", category);
        List<String> categories = new ArrayList<String>();
        categories.add("book");
        categories.add("movie");
        categories.add("music");
        model.addAttribute("categories", categories);

        switch (category){
            case "book":
                books = bookJdbc.findBooks((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", books);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", 2);
                break;
            case "movie":
                movies = movieJdbc.findMovies((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", movies);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", 2);
                break;
            case "music":
                musics = musicJdbc.findMusics((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", musics);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", 2);
                break;
            default:
                model.addAttribute("categoryName", "Unknown");
        }

        return "itemsPage";
    }
}
