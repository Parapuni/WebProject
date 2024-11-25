package cmt.db.jdbc;

import cmt.db.api.ItemHandler;
import cmt.entity.Item;
import cmt.entity.SimpleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

@Repository
public class ItemJdbc implements ItemHandler {
    private final String INSERT_ITEM = "insert into Item(`title`,`releaseDate`,`stars`,`rating`,`coverimagine`) values(?,?,?,?,?)";
    private final String DELETE_ITEM = "delete from Item where iid = ?";
    private final String UPDATE_ITEM = "update Item set title = ?,releaseDate = ?,stars = ?,rating = ?,coverImagine = ? where iid = ?";
    private final String SELECT_STARS = "select stars from Item where iid = ?";
    private final String UPDATE_STARS = "update Item set stars = ?,rating =? where iid = ?";
    private final String IS_BOOK = "select iid from book where iid = ?";
    private final String IS_MOVIE = "select iid from movie where iid = ?";
    private final String IS_MUSIC = "select iid from music where iid = ?";
    private final String GET_TEN = "select iid,title,rating,coverImagine from item order by rating desc limit 6";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isBook(long iid){
        boolean isBook = true;
        try {
            jdbcTemplate.queryForLong(IS_BOOK, iid);
        }catch (DataAccessException dae){
            isBook = false;
        }finally {
            return isBook;
        }
    }
    public boolean isMovie(long iid){
        boolean isMovie = true;
        try {
            jdbcTemplate.queryForLong(IS_MOVIE, iid);
        }catch (DataAccessException dae){
            isMovie = false;
        }finally {
            return isMovie;
        }
    }
    public boolean isMusic(long iid){
        boolean isMusic = true;
        try {
            jdbcTemplate.queryForLong(IS_MUSIC, iid);
        }catch (DataAccessException dae){
            isMusic = false;
        }finally {
            return isMusic;
        }
    }
    @Override
    public String getType(long iid) {
        if(isBook(iid))
            return "Book";
        if(isMovie(iid))
            return "Movie";
        if(isMusic(iid))
            return "Music";
        return "";
    }

    public long addItemReturnPrimaryKey(Item item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDate(2, item.getReleaseDate());
            ps.setString(3, Arrays.toString(item.getStars()));
            ps.setDouble(4, item.getRating());
            ps.setString(5, item.getCoverImagine());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void removeItem(long iid) {
        jdbcTemplate.update(DELETE_ITEM, iid);
    }

    /**
     * 更新item记录，不允许更改iid
     *
     * @param item
     */
    @Override
    public void updateItem(Item item) {
        jdbcTemplate.update(UPDATE_ITEM, item.getTitle(), item.getReleaseDate(), Arrays.toString(item.getStars()), item.getRating(), item.getCoverImagine(),item.getIid());
    }
    @Override
    public void updateRating(long iid, int star) {
        star --;
        String sStars = jdbcTemplate.queryForObject(SELECT_STARS, String.class, iid);
        sStars = sStars.substring(1, sStars.length() - 1);
        int[] stars = new int[5];
        String[] s = sStars.split(",");
        double rating = 0.0;
        long sum = 0;
        for (int i = 0; i < 5; i++) {
            stars[i] = Integer.parseInt(s[i].trim());
            if (i == star)
                stars[i] ++;
            sum += stars[i];
            rating += (i + 1) * stars[i];
        }
        rating = rating / sum;
        System.out.println(Arrays.toString(stars));
        jdbcTemplate.update(UPDATE_STARS, Arrays.toString(stars), rating,iid);
    }
    /**
     *
     * @param iid
     * @param star
     * @param isAdd 1表示增加，-1表示减少
     */
    @Override
    public void updateRating(long iid, int star, int isAdd) {
        System.out.println(star);
        star --;
        String sStars = jdbcTemplate.queryForObject(SELECT_STARS, String.class, iid);
        sStars = sStars.substring(1, sStars.length() - 1);
        int[] stars = new int[5];
        String[] s = sStars.split(",");
        double rating = 0.0;
        long sum = 0;
        for (int i = 0; i < 5; i++) {
            stars[i] = Integer.parseInt(s[i].trim());
            if (i == star)
                stars[i] += isAdd;
            sum += stars[i];
            rating += (i + 1) * stars[i];
        }
        rating = rating / sum;
        System.out.println(Arrays.toString(stars));
        jdbcTemplate.update(UPDATE_STARS, Arrays.toString(stars), rating,iid);
    }

    @Override
    public Item getItemById(long iid) {
        return null;
    }

    public List<SimpleItem> getRecommendedItems(){
        List<SimpleItem> items = jdbcTemplate.query(GET_TEN,new SimpleItemRowMapper());
        for (SimpleItem item:items
             ) {
            item.setType(getType(item.getIid()));
        }
        return items;
    }
    private final static class SimpleItemRowMapper implements RowMapper<SimpleItem> {

        @Override
        public SimpleItem mapRow(ResultSet resultSet, int i) throws SQLException {
            return new SimpleItem(resultSet.getLong("iid"),resultSet.getString("title"), resultSet.getDouble("rating"),resultSet.getString("coverImagine"));
        }
    }
}
