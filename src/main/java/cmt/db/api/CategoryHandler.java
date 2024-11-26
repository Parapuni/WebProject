package cmt.db.api;

import cmt.entity.Item;

import java.util.List;

/**
 * Category数据操作接口
 */
public interface CategoryHandler {
    /**
     * @return 获取分类列表
     */
    List<String> getTagLib();
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
     * @param iid 物品id
     * @param categories 种类列表
     */
    void addItemCategories(long iid, List<String> categories);

    /**
     * 通过物品的iid移除物品的分类
     * @param iid 物品id
     */
    void removeItem(long iid);

    /**
     * 通过物品对象更新物品的分类
     * @param item 更新后的物品分类
     */
    void updateItemCategories(Item item);
}
