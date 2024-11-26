package cmt.db.api;

import cmt.entity.Item;

/**
 * item数据操作接口
 */

public interface ItemHandler {

    /**
     * 通过item的iid获取
     * @param iid 物品iid
     * @return 物品子类类型
     */

    String getType(long iid);

    /**
     *更具物品对象添加物品并且返回id
     * @param item 添加的物品
     * @return 新添加物品id
     */
    long addItemReturnPrimaryKey(Item item);

    /**
     *根据物品id移除item
     * @param iid 物品的id
     */
    void removeItem(long iid);

    /**
     *通过物品对象更新物品数据
     * @param item 更新数据的物品对象
     */
    void updateItem(Item item);

    /**
     *根据物品id更新物品星星数
     * @param iid 物品id
     * @param star 星星数
     */
    void updateRating(long iid, int star);

    /**
     *通过物品id更新星星数
     * @param iid 物品id
     * @param star 变化星星数
     * @param isAdd 是添加还是减少
     */
    void updateRating(long iid, int star, int isAdd);

    /**
     *通过id获取物品
     * @param iid 物品id
     * @return 获取的物品对象
     */
    Item getItemById(long iid);
}
