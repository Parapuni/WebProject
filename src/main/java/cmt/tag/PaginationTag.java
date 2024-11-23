package cmt.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PaginationTag extends SimpleTagSupport {
    private int maxPagesVisible = 5;
    private int index;
    private int pageNum;

    public int getMaxPagesVisible() {
        return maxPagesVisible;
    }

    public void setMaxPagesVisible(int maxPagesVisible) {
        this.maxPagesVisible = maxPagesVisible;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * 计算分页栏首尾页码
     * 循环队列式的，控制页码数为不超过maxPagesVisible+2，
     * example：
     * 1 2 3 4 5 6 --> 5 6 7 8 9 10
     * @throws IOException
     * @throws JspException
     */
    @Override
    public void doTag() throws IOException, JspException {
        int cnt = index / maxPagesVisible;
        int remain = index % maxPagesVisible;
        cnt += (remain == 0) ? 0 : 1;
        int first = (cnt - 1) * maxPagesVisible;
        int last = cnt * maxPagesVisible+1;
        first = first>0? first :1;
        last = last < pageNum ? last : pageNum;
        getJspContext().setAttribute("start",first);
        getJspContext().setAttribute("end",last);
    }
}
