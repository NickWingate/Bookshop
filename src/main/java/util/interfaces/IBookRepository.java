package main.java.util.interfaces;

import main.java.domain.entities.Book;

public interface IBookRepository extends IBaseRepository<Book> {
    public boolean DeleteByBarcode(String barcode);
}
