package cmt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {
    @RequestMapping(value = "/submit-review", method = RequestMethod.GET)
    public String showSubmitReviewPage() {
        return "submit-review"; // 渲染 WEB-INF/views/submit-review.jsp
    }
}
