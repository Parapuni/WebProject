package cmt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {
    @RequestMapping(value = "/editreview", method = RequestMethod.GET)
    public String showSubmitReviewPage() {
        return "editReview";
    }

}
