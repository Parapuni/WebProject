package cmt.db.api;


import cmt.entity.Book;

import java.util.Date;
import java.util.List;

/**
 * Book数据操作接口
 */
public interface BookHandler {

    /**
     * @return 返回所有book数量
     */
    int countTotal();

    /**
     * 添加书籍
     * @param book 待添加的书籍对象
     */
    void addBook(Book book);

    /**
     * 移除书籍
     * @param iid 待移除书籍的iid
     */
    void removeBook(long iid);

    /**
     * 更新书籍信息
     * @param book 更新后的书籍信息对象
     */

    void updateBook(Book book);

    /**
     * 更新评分
     * @param iid 待更新书籍的iid
     * @param rating 更新后的评分
     */

    void updateRating(long iid, int rating);

    /**
     * 通过id查找书籍
     * @param iid 书籍id
     * @return 待查询书籍对象
     */

    Book findBookById(long iid);

    /**
     * 通过偏移量和查询上限查询书籍
     * @param offset 偏移量
     * @param length 上限
     * @return 查询的书籍对象列表
     */

    List<Book> findBooks(int offset, int length);

    /**
     * 通过类型查找书籍
     * @param offset 偏移量
     * @param length 上限
     * @param NameOfCategories 类型
     * @return 查询的书籍对象列表
     */

    List<Book> findBooksByCategories(int offset, int length, List<String> NameOfCategories);

    /**
     * 对通过类型查询到的书籍计数
     * @param NameOfCategories 书籍类型
     * @return 查询到的书籍数
     */

    int countByCategories(List<String> NameOfCategories);

    /**
     * 通过标题查询书籍
     * @param offset 偏移量
     * @param length 上限
     * @param title 标题
     * @return 通过标题查询到的书籍列表
     */

    List<Book> findBooksByTitle(int offset, int length, String title);

    /**
     * 对通过标题查询到的书籍计数
     * @param title 标题
     * @return 查询到的书籍数
     */

    int countByTitle(String title);

    /**
     * 通过作者查询书籍
     * @param offset 偏移量
     * @param length 上限
     * @param authors 作者
     * @return 查询到的书籍列表
     */

    List<Book> findBooksByAuthors(int offset, int length, String authors);

    /**
     * 对通过作者查询到的书籍计数
     * @param authors 作者
     * @return 查询到的书籍数
     */

    int countByAuthors(String authors);

    /**
     * 通过出版社查询书籍
     * @param offset 偏移量
     * @param length 上限
     * @param publisher 出版社
     * @return 查询到的书籍列表
     */

    List<Book> findBooksByPublisher(int offset, int length, String publisher);

    /**
     * 对通过出版社查询到的书籍计数
     * @param publisher 出版社
     * @return 查询到的书籍数
     */

    int countByPublisher(String publisher);

    /**
     * 通过上传时间年份查询书籍
     * @param year 年份
     * @return 查询到的书籍列表
     */

    List<Book> findBooksByYear(int year);

    /**
     * 对通过上传时间年份查询到的书籍计数
     * @param year 年份
     * @return 查询到的书籍数
     */
    int countByYear(int year);
}
