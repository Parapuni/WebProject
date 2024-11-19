package cmt.db.jdbc;

import cmt.db.api.BookHandler;
import cmt.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BookJdbc implements BookHandler {

    private final String INSERT_ITEM = "insert into Item(`title`,`releaseDate`,`stars`,`rating`,`coverimagine`) values(?,?,?,?,?)";
    private final String INSERT_BOOK = "insert into Book values(?,?,?,?)";
    private final String DELETE_ITEM = "delete from Item where iid = ?";
    private final String DELETE_BOOK = "delete from Book where iid = ?";
    private final String SELECT_STARS = "select stars from Item where iid = ?";
    private final String UPDATE_STARS = "update Item set stars = ?,rating =? where iid = ?";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CategoryJdbc categoryJdbc;
    @Autowired
    public BookJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook(Book book) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setDate(2,   book.getReleaseDate());
            ps.setString(3, Arrays.toString(book.getStars()));
            ps.setDouble(4, book.getRating());
            ps.setString(5, book.getCoverImagine().toString());
            return ps;
        }, keyHolder);
        jdbcTemplate.update(INSERT_BOOK, keyHolder.getKey().longValue(), book.getAuthors(), book.getPublisher(),book.getIntroduction());
        categoryJdbc.addItemCategories(keyHolder.getKey().longValue(), book.getCategories());

    }

    @Override
    public void removeBook(long iid) {
        jdbcTemplate.update(DELETE_BOOK, iid);
        categoryJdbc.removeItem(iid);
        jdbcTemplate.update(DELETE_ITEM, iid);
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void updateRating(long iid, int rating) {

    }

    @Override
    public Book findBookById(long iid) {
        return null;
    }

    @Override
    public List<Book> findBooks(int offset, int length) {
        return null;
    }

    @Override
    public List<Book> findBooksByCategories(int offset, int length, List<String> NameOfCategories) {
        return null;
    }

    @Override
    public List<Book> findBooksByTitle(int offset, int length, String title) {
        return null;
    }

    @Override
    public List<Book> findBooksByAuthors(int offset, int length, String authors) {
        return null;
    }

    @Override
    public List<Book> findBooksByPublisher(int offset, int length, String publisher) {
        return null;
    }

    private static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();

            return book;
        }
    }
}
