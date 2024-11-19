package cmt.db.api;

import cmt.entity.Item;

public interface ItemHandler {
    long addItemReturnPrimaryKey(Item item);

    void removeItem(long iid);

    void updateItem(Item item);

    void updateRating(long iid, int star);
}
