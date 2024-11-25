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
import java.util.ArrayList;
import java.util.List;

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
    private Item item;

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

        switch (category) {
            case "Book":
                books = bookJdbc.findBooks((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", books);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", (int) Math.ceil((double) bookJdbc.countTotal() / PAGE_SIZE));
                break;
            case "Movie":
                movies = movieJdbc.findMovies((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", movies);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", (int) Math.ceil((double) movieJdbc.countTotal() / PAGE_SIZE));
                break;
            case "Music":
                musics = musicJdbc.findMusics((page - 1) * PAGE_SIZE, PAGE_SIZE);
                model.addAttribute("items", musics);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", (int) Math.ceil((double) musicJdbc.countTotal() / PAGE_SIZE));
                break;
            default:
                model.addAttribute("categoryName", "Unknown");
        }

        return "itemsPage";
    }

    @RequestMapping(value = "/filt_items", method = GET)
    public String processFiltTiems(@RequestParam(value = "category") String category,
                                   @RequestParam(value = "tags") List<String> tags,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   Model model) {
        List<String> selectedTags = tags != null ? tags : new ArrayList<>();
        model.addAttribute("selectedTags", selectedTags);
        model.addAttribute("category", category);
        model.addAttribute("currentCategory", category);

        return "itemsPage";
    }

    @RequestMapping(value = "/search", method = GET)
    public String processSearching(@RequestParam(value = "type") String category,
                                   @RequestParam(value = "query") String query,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   Model model) {
        model.addAttribute("category", category);
        List<String> categories = new ArrayList<String>();
        categories.add("Book");
        categories.add("Movie");
        categories.add("Music");
        model.addAttribute("categories", categories);

        if (query == null || query.trim().isEmpty()){
            model.addAttribute("error", "Search query cannot be empty.");
            return "/";
        }
        switch (category){
            case "Book":
                List<Book> books = bookJdbc.findBooksByTitle((page - 1) * PAGE_SIZE, PAGE_SIZE, query);
                model.addAttribute("totalPages", (int) Math.ceil((double) bookJdbc.countByTitle(query) / PAGE_SIZE));
                model.addAttribute("items", books);
                break;
            case "Movie":
                List<Movie> movies = movieJdbc.findMoviesByTitle((page - 1) * PAGE_SIZE, PAGE_SIZE, query);
                model.addAttribute("totalPages", (int) Math.ceil((double) movieJdbc.countByTitle(query) / PAGE_SIZE));
                model.addAttribute("items", movies);
                break;
            case "Music":
                List<Music> musics = musicJdbc.findMusicsByTitle((page - 1) * PAGE_SIZE, PAGE_SIZE, query);
                model.addAttribute("totalPages", (int) Math.ceil((double) musicJdbc.countByTitle(query) / PAGE_SIZE));
                model.addAttribute("items", musics);
                break;
        }
        model.addAttribute("currentPage", page);

        return "itemsPage";
    }

    @RequestMapping(value = "/item-details", method = GET)
    public String showItemDetials(@RequestParam(value = "id") Integer iid,
                                  @RequestParam(value = "category") String category,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  Model model) {
        System.out.println("Item ID: " + iid);
        switch (category) {
            case "Book":
                item = bookJdbc.findBookById(iid);
                break;
            case "Movie":
                item = movieJdbc.findMovieById(iid);
                break;
            case "Music":
                item = musicJdbc.findMusicById(iid);
                break;
            default:
                model.addAttribute("categoryName", "Unknown");
        }
        List<Integer> percentages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            double percentage = item.getTotalRating() > 0
                    ? (item.getStars()[i] / (double) item.getTotalRating()) * 100
                    : 0;
            percentages.add((int) percentage);
        }
        model.addAttribute("percentages", percentages);
        model.addAttribute("item", item);
        comments = commentJdbc.findCommentsByItemId(iid, (page - 1) * PAGE_SIZE, PAGE_SIZE);
        System.out.println(comments.size());
        model.addAttribute("comments", comments);
        model.addAttribute("currentPage", page);
        model.addAttribute("category", category);
        model.addAttribute("totalPages", (int) Math.ceil((double) commentJdbc.countByItemId(iid) / PAGE_SIZE));

        return "itemReview";
    }

    @RequestMapping(value = "/toPage", method = GET)
    public String toPage(@RequestParam("iid") Integer iid,
                         Model model){
        String category = itemJdbc.getType(iid);
        System.out.println(category);
        return showItemDetials(iid,category,1,model);
    }

    /**
     * 显示上传封面页面
     */
    @RequestMapping(value = "/upload-cover", method = RequestMethod.GET)
    public String showUploadCoverPage(@RequestParam("iid") long iid, Model model) {
        Item item = itemJdbc.getItemById(iid); // 根据 IID 获取对应 Item
        if (item == null) {
            model.addAttribute("error", "找不到对应的作品！");
            return "error";
        }
        model.addAttribute("item", item);
        return "uploadCover";
    }

    /**
     * 处理上传封面
     */
    @RequestMapping(value = "/upload-cover", method = RequestMethod.POST)
    public String processUploadCover(@RequestParam("coverFile") MultipartFile coverFile,
                                     @RequestParam("iid") long iid,
                                     HttpSession session,
                                     Model model) {

        Item item = itemJdbc.getItemById(iid); // 根据 IID 获取对应 Item
        if (item == null) {
            model.addAttribute("error", "找不到对应的作品！");
            return "error";
        }

        if (coverFile.isEmpty()) {
            model.addAttribute("error", "请选择一个封面文件进行上传！");
            return "uploadCover";
        }

        try {
            // 存储路径：确保/resources/imagines 目录存在
            String uploadDir = getClass().getClassLoader().getResource("").getPath() + "imagines/";
            String fileName = "IID" + iid + "cover" + getFileExtension(coverFile.getOriginalFilename()); // 生成文件名
            File destination = new File(uploadDir + fileName);

            // 存储文件
            coverFile.transferTo(destination);

            // 更新数据库中的封面路径
            item.setCoverImagine(fileName);
            itemJdbc.updateItem(item);

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "上传封面失败！");
            return "uploadCover";
        }

        model.addAttribute("success", "封面上传成功！");
        return "redirect:/itemmanage"; // 返回到作品管理页面
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        return (index == -1) ? "" : fileName.substring(index);
    }
}
