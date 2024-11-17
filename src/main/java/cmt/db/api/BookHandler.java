package cmt.db.api;


import cmt.entity.Book;

import java.util.List;

/**
 * Book数据操作接口
 */
public interface BookHandler {
    void addBook(Book book);

    void removeBook(long iid);

    void updateBook(Book book);

    void updateRating(long iid, int rating);

    Book findBookById(long iid);

    List<Book> findBooks(int offset, int length);

    List<Book> findBooksByCategories(int offset, int length, List<String> NameOfCategories);

    List<Book> findBooksByTitle(int offset, int length, String title);

    List<Book> findBooksByAuthors(int offset, int length, String authors);

    List<Book> findBooksByPublisher(int offset, int length, String publisher);
}
