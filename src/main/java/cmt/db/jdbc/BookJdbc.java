package cmt.db.jdbc;

import cmt.db.api.BookHandler;
import cmt.entity.Book;
import cmt.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class BookJdbc implements BookHandler {
    private final String COUNT_TOTAL = "select COUNT(*) from Book";
    private final String COUNT_BY_CATEGORY = "select COUNT(*) from " +
            "(select b.* from Book b natural join Category_Item ci where ci.name in (?) ) " +
            "as cb natural join Item i";
    private final String COUNT_BY_TITLE = "select COUNT(*) from Book b natural join Item i where i.title like ? ";
    private final String COUNT_BY_AUTHORS = "select COUNT(*) from Book b natural join Item i where b.authors like ? ";
    private final String COUNT_BY_PUBLISHER = "select COUNT(*) from Book b natural join Item i where b.publisher like ? ";
    private final String COUNT_BY_YEAR = "select count(*) from Book b natural join Item i where Year(i.releaseDate) = ? ";
    private final String INSERT_BOOK = "insert into Book values(?,?,?,?)";
    private final String DELETE_BOOK = "delete from Book where iid = ?";
    private final String UPDATE_BOOK = "update Book set `authors` = ?,`publisher` = ?,`introduction` = ?  where iid = ?;";
    private final String SELECT_BOOK_BY_ID = "select * from Book b natural join item i where b.iid = ?;";
    private final String SELECT_BOOKS = "select * from Book m natural join item i order by i.releaseDate desc limit ? offset ?;";
    private final String SELECT_BOOKS_BY_CATEGORY = "select * from " +
            "(select b.* from Book b natural join Category_Item ci where ci.name in (?) limit ? offset ?) " +
            "as cb natural join Item i";
    private final String SELECT_BOOKS_BY_TITLE = "select * from Book b natural join Item i where i.title like ? limit ? offset ?";
    private final String SELECT_BOOKS_BY_AUTHORS = "select * from Book b natural join Item i where b.authors like ? limit ? offset ?";
    private final String SELECT_BOOKS_BY_PUBLISHER = "select * from Book b natural join Item i where b.publisher like ? limit ? offset ?";
    private final String SELECT_BOOKS_BY_YEAR = "select * from Book b natural join Item i where Year(i.releaseDate) = ? ";
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CategoryJdbc categoryJdbc;
    @Autowired
    private ItemJdbc itemJdbc;

    @Autowired
    public BookJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int countTotal() {
        return jdbcTemplate.queryForInt(COUNT_TOTAL);
    }

    @Override
    public void addBook(Book book) {
        long iid = itemJdbc.addItemReturnPrimaryKey(book);
        System.out.println(iid);
        jdbcTemplate.update(INSERT_BOOK, iid, book.getAuthors(), book.getPublisher(), book.getIntroduction());
        categoryJdbc.addItemCategories(iid, book.getCategories());
    }

    @Override
    public void removeBook(long iid) {
        jdbcTemplate.update(DELETE_BOOK, iid);
        categoryJdbc.removeItem(iid);
        itemJdbc.removeItem(iid);
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK,book.getAuthors(),book.getPublisher(),book.getIntroduction(),book.getIid());
        categoryJdbc.updateItemCategories(book);
        itemJdbc.updateItem(book);
    }

    @Override
    public void updateRating(long iid, int rating) {
        itemJdbc.updateRating(iid, rating);
    }

    @Override
    public Book findBookById(long iid) {
        Book book = null;
        try {
            book = jdbcTemplate.queryForObject(SELECT_BOOK_BY_ID, new BookRowMapper(), iid);
            categoryJdbc.setCategory(book);
        }catch (DataAccessException dae) {
        }finally {
            return book;
        }
    }

    @Override
    public List<Book> findBooks(int offset, int length) {
        List<Book> books = jdbcTemplate.query(SELECT_BOOKS, new BookRowMapper(), length ,offset);
        categoryJdbc.setCategory(books);
        return books;
    }

    @Override
    public List<Book> findBooksByCategories(int offset, int length, List<String> NameOfCategories) {
        String categories = String.join(",", NameOfCategories);
        List<Book> books = jdbcTemplate.query(SELECT_BOOKS_BY_CATEGORY, new BookRowMapper(), categories, length, offset);
        categoryJdbc.setCategory(books);
        return books;
    }

    @Override
    public int countByCategories(List<String> NameOfCategories) {
        String categories = String.join(",", NameOfCategories);
        return jdbcTemplate.queryForInt(COUNT_BY_CATEGORY,categories);
    }

    @Override
    public List<Book> findBooksByTitle(int offset, int length, String title) {
        List<Book> books = jdbcTemplate.query(SELECT_BOOKS_BY_TITLE, new BookRowMapper(), "%" + title + "%", length, offset);
        categoryJdbc.setCategory(books);
        return books;
    }

    @Override
    public int countByTitle(String title) {
        return jdbcTemplate.queryForInt(COUNT_BY_TITLE,"%" + title + "%");
    }

    @Override
    public List<Book> findBooksByAuthors(int offset, int length, String authors) {
        List<Book> books = jdbcTemplate.query(SELECT_BOOKS_BY_AUTHORS, new BookRowMapper(), "%" + authors + "%", length, offset);
        categoryJdbc.setCategory(books);
        return books;
    }

    @Override
    public int countByAuthors(String authors) {
        return jdbcTemplate.queryForInt(COUNT_BY_AUTHORS,"%" + authors + "%");
    }

    @Override
    public List<Book> findBooksByPublisher(int offset, int length, String publisher) {
        List<Book> books = jdbcTemplate.query(SELECT_BOOKS_BY_PUBLISHER, new BookRowMapper(), "%" + publisher + "%", length, offset);
        categoryJdbc.setCategory(books);
        return books;
    }

    @Override
    public int countByPublisher(String publisher) {
        return jdbcTemplate.queryForInt(COUNT_BY_PUBLISHER,"%" + publisher + "%");
    }

    @Override
    public List<Book> findBooksByYear(int year) {
        List<Book> books = jdbcTemplate.query(SELECT_BOOKS_BY_YEAR,new BookRowMapper(),year);
        categoryJdbc.setCategory(books);
        return books;
    }

    @Override
    public int countByYear(int year) {
        return jdbcTemplate.queryForInt(COUNT_BY_YEAR,year);
    }


    private static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book book = new Book();
                book.setIid(resultSet.getLong("iid"));
                book.setTitle(resultSet.getString("title"));
                book.setReleaseDate(resultSet.getDate("releaseDate"));
                book.setStars(resultSet.getString("stars"));
                book.setRating(resultSet.getDouble("rating"));
                book.setCoverImagine(resultSet.getString("coverImagine"));
                book.setAuthors(resultSet.getString("authors"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setIntroduction(resultSet.getString("introduction"));
            return book;
        }
    }
}
