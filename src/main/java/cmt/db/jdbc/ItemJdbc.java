package cmt.db.jdbc;

import cmt.db.api.ItemHandler;
import cmt.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;

@Repository
public class ItemJdbc implements ItemHandler {
    private final String INSERT_ITEM = "insert into Item(`title`,`releaseDate`,`stars`,`rating`,`coverimagine`) values(?,?,?,?,?)";
    private final String DELETE_ITEM = "delete from Item where iid = ?";
    private final String SELECT_STARS = "select stars from Item where iid = ?";
    private final String UPDATE_STARS = "update Item set stars = ?,rating =? where iid = ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long addItemReturnPrimaryKey(Item item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDate(2, item.getReleaseDate());
            ps.setString(3, Arrays.toString(item.getStars()));
            ps.setDouble(4, item.getRating());
            ps.setString(5, item.getCoverImagine().toString());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void removeItem(long iid) {
        jdbcTemplate.update(DELETE_ITEM, iid);
    }

    @Override
    public void updateItem(Item item) {
        /*
        TODO
         */
    }

    @Override
    public void updateRating(long iid, int star) {
        String sStars = jdbcTemplate.queryForObject(SELECT_STARS, String.class, iid);
        sStars.substring(1, sStars.length() - 1);
        int[] stars = new int[5];
        String[] s = sStars.split(",");
        double rating = 0.0;
        long sum = 0;
        for (int i = 0; i < 5; i++) {
            stars[i] = Integer.parseInt(s[i]);
            if (i == star)
                stars[i]++;
            sum += stars[i];
            rating += (i + 1) * stars[i];
        }
        rating = rating / sum;
        jdbcTemplate.update(UPDATE_STARS, Arrays.toString(stars), rating);
    }

}
