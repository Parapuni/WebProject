package cmt.db.api;


import cmt.entity.Book;
import cmt.entity.Music;

import java.util.Date;
import java.util.List;

/**
 * Music数据操作接口
 */
public interface MusicHandler {
    /**
     * @return 音乐对象总个数
     */
    int countTotal();

    /**
     * 通过音乐对象添加音乐
     * @param music 待添加的音乐对象
     */
    void addMusic(Music music);

    /**
     * 移除通过id音乐
     * @param iid 待移除的音乐id
     */

    void removeMusic(long iid);

    /**
     * 通过新音乐对象更新
     * @param music 更新信息后的音乐对象
     */

    void updateMusic(Music music);

    /**
     * 通过音乐id更新评分
     * @param iid 音乐id
     * @param rating 新的评分
     */
    void updateRating(long iid, int rating);

    /**
     * 通过id查找音乐
     * @param iid 音乐id
     * @return 音乐对象
     */

    Music findMusicById(long iid);

    /**
     * 通过偏移值和上限查找音乐
     * @param offset 偏移值
     * @param length 上限
     * @return 音乐对象列表
     */

    List<Music> findMusics(int offset, int length);

    /**
     * 通过类型查找音乐
     * @param offset 偏移值
     * @param length 上限
     * @param NameOfCategories 类型列表
     * @return 音乐列表
     */

    List<Music> findMusicsByCategories(int offset, int length, List<String> NameOfCategories);

    /**
     * 对通过类型查找到的音乐计数
     * @param NameOfCategories 类型列表
     * @return 音乐数
     */

    int countByCategories(List<String> NameOfCategories);

    /**
     * 通过标题查找音乐
     * @param offset 偏移值
     * @param length 上限
     * @param title 标题
     * @return 音乐列表
     */

    List<Music> findMusicsByTitle(int offset, int length, String title);

    /**
     * 对通过标题查找到的音乐计数
     * @param title 标题
     * @return 音乐数
     */

    int countByTitle(String title);

    /**
     * 通过歌手查找音乐
     * @param offset 偏移值
     * @param length 上限
     * @param artists 歌手名
     * @return 音乐列表
     */

    List<Music> findMusicsByArtists(int offset, int length, String artists);

    /**
     * 对通过歌手查找到的音乐计数
     * @param artists 歌手名
     * @return 音乐数
     */

    int countByArtists(String artists);

    /**
     * 通过专辑查找音乐
     * @param offset 偏移值
     * @param length 上限
     * @param Album 专辑名
     * @return 音乐列表
     */
    List<Music> findMusicsByAlbum(int offset, int length, String Album);

    /**
     * 对通过专辑查找到的音乐计数
     * @param Album 专辑名
     * @return 音乐书
     */
    int countByAlbum(String Album);

    /**
     * 通过上传年份查找音乐
     * @param year 年份
     * @return 音乐列表
     */
    List<Music> findMusicsByYear(int year);

    /**
     * 对通过上传年份查找到的音乐计数
     * @param year 年份
     * @return 音乐数
     */
    int countByYear(int year);
}
