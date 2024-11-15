package cmt.db.jdbc;

import cmt.db.api.ItemDataUpdatar;

public class ItemJdbc implements ItemDataUpdatar {

    /**
     * 计算新评分，在更新stars数组时需要调用
     */
    public double caculateScore(int[] stars) {
        double sum = 0;
        for (int i = 0; i < stars.length; i++)
            sum += stars[i] * (i + 1);
        return sum / 5.0d;
    }
}
