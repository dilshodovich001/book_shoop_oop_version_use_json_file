package org.example.repository;

import org.example.db.DbBookConnection;
import org.example.entity.BookEntity;

import java.util.List;

public class BookRepository {
    private final DbBookConnection dbBookConnection = new DbBookConnection("book.json");
    public void save(BookEntity bookEntity){
        dbBookConnection.writeValue(List.of(bookEntity));
    }

    public List<BookEntity> getData() {
        return dbBookConnection.readData();
    }

    public void saveAll(List<BookEntity> bookEntities){
        dbBookConnection.clear();
        dbBookConnection.writeValue(bookEntities);
    }
}
