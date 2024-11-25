package cmt.db.api;


import cmt.entity.Book;
import cmt.entity.Movie;

import java.util.Date;
import java.util.List;

/**
 * Movie数据操作接口
 */
public interface MovieHandler {
    /**
     * @return 获取movie的个数
     */
    int countTotal();

    /**
     * 添加movie
     * @param movie 添加的movie对象
     */
    void addMovie(Movie movie);


    /**
     * 移除movie通过id
     * @param iid 待移除movie的iid
     */
    void removeMovie(long iid);

    /**
     * 更新movie对象
     * @param movie 更新后的movie对象
     */
    void updateMovie(Movie movie);

    /**
     * 更新movie评分
     * @param iid 待更新评分movie的iid
     * @param rating 更新后评分
     */
    void updateRating(long iid, int rating);

    /**
     * 通过id查找movie
     * @param iid movie的id
     * @return 影视对象
     */

    Movie findMovieById(long iid);

    /**
     * 通过偏移值和上限获取movie
     * @param offset 偏移值
     * @param length 上限
     * @return 影视对象列表
     */

    List<Movie> findMovies(int offset, int length);

    /**
     * 通过种类查找movie
     * @param offset 偏移值
     * @param length 上限
     * @param NameOfCategories 种类列表
     * @return 影视对象列表
     */

    List<Movie> findMoviesByCategories(int offset, int length, List<String> NameOfCategories);

    /**
     * 对通过种类获取的movie计数
     * @param NameOfCategories 种类列表
     * @return 影视对象列表
     */

    int countByCategories(List<String> NameOfCategories);

    /**
     * 通过标题查找影视
     * @param offset 偏移值
     * @param length 上限
     * @param title 标题
     * @return 影视列表
     */

    List<Movie> findMoviesByTitle(int offset, int length, String title);

    /**
     * 对通过标题查找的影视计数
     * @param title 标题
     * @return 影视对象个数
     */

    int countByTitle(String title);

    /**
     * 通过导演查找movie
     * @param offset 偏移值
     * @param length 上限
     * @param director 导演名
     * @return 影视列表
     */

    List<Movie> findMoviesByDirector(int offset, int length, String director);

    /**
     * 对通过导演查找到的movie计数
     * @param director 导演名
     * @return 影视对象数
     */

    int countByDirector(String director);

    /**
     * 通过演员查找movie
     * @param offset 偏移值
     * @param length 上限
     * @param cast 演员名字
     * @return 查到的影视对象列表
     */

    List<Movie> findMoviesByCast(int offset, int length, String cast);

    /**
     * 对通过演员查找到的movie计数
     * @param cast 演员名字
     * @return 影视个数
     */

    int countByCast(String cast);

    /**
     * 通过编剧查找movie
     * @param offset 偏移值
     * @param length 上限
     * @param writers 编剧名字
     * @return 影视列表
     */

    List<Movie> findMoviesByWriters(int offset, int length, String writers);

    /**
     *对通过编剧查找到的movie计数
     * @param writers 编剧名字
     * @return 影视数
     */

    int countByWriters(String writers);

    /**
     * 通过上传年份查找影视
     * @param year 年份
     * @return 影视列表
     */

    List<Movie> findMoviesByYear(int year);

    /**
     * 对通过上传年份查找到的影视计数
     * @param year 年份
     * @return 影视数
     */

    int countByYear(int year);
}
