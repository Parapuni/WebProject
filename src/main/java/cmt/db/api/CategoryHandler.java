package cmt.db.api;

import java.util.List;

public interface CategoryHandler {
    List<String> getCategories(long iid);
}
