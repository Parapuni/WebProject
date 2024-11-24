package cmt.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import java.io.IOException;

public class RatingTag extends SimpleTagSupport {
    private double rating; // 评分

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        int fullStars = (int) rating; // 整星
        boolean hasHalfStar = (rating - fullStars) >= 0.5; // 是否有半星
        int emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0); // 空星

        // 输出整星
        for (int i = 0; i < fullStars; i++) {
            out.write("<span class='fa fa-star checked'></span>");
        }

        // 输出半星
        if (hasHalfStar) {
            out.write("<span class='fa fa-star-half-alt checked'></span>");
        }

        // 输出空星
        for (int i = 0; i < emptyStars; i++) {
            out.write("<span class='fa fa-star'></span>");
        }
    }
}
