package cmt.db.jdbc;

import cmt.db.api.BookHandler;
import cmt.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookJdbc implements BookHandler {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void removeBook(long iid) {

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
