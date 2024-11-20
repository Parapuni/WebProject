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

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ReviewController {
    @RequestMapping(value = "/submit-review", method = RequestMethod.GET)
    public String showSubmitReviewPage() {
        return "submit-review"; // 渲染 WEB-INF/views/submit-review.jsp
    }
}
