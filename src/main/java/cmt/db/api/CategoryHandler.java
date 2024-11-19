package cmt.db.api;

import java.util.List;

public interface CategoryHandler {
    /**
     * 获取指定Item的分类
     *
     * @param iid Item的id
     * @return Item的分类
     */
    List<String> getCategories(long iid);

    /**
     * 为一个作品（Item）添加分类
     *
     * @param iid
     * @param categories
     */
    void addItemCategories(long iid, List<String> categories);

    void removeItem(long iid);

}
