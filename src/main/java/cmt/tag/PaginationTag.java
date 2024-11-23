package cmt.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PaginationTag extends SimpleTagSupport {
    private final int MAX_VISIBLE_PAGES = 5;
    private String category;
    private int index;
    private int pageNum;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public void doTag() throws IOException {
        int cnt = index / MAX_VISIBLE_PAGES;
        int remain = index % MAX_VISIBLE_PAGES;
        cnt += remain == 0 ? 0 : 1;
        int first = (cnt - 1) * MAX_VISIBLE_PAGES + 1;
        int last = cnt * MAX_VISIBLE_PAGES;
        last = last < pageNum ? last : pageNum;
        String script =
                "          <li class=\"page-item %s\">\n" +
                        "            <a class=\"page-link\" href=\"<c:url value='/items?page=%d&category=%s' />\">%d</a>\n" +
                        "          </li>\n";
        for (int i = first; i <= last; i++) {
            if (i == getIndex())
                getJspContext().getOut().print(String.format(script, "active", i, getCategory(), i));
            else
                getJspContext().getOut().print(String.format(script, "", i, getCategory(), i));
        }
    }
}
