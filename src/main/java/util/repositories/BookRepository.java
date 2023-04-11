package main.java.util.repositories;

import main.java.domain.entities.Book;
import main.java.util.interfaces.IBookParser;
import main.java.util.interfaces.IBookRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookRepository implements IBookRepository {

    private IBookParser _bookParser;
    private String _stockFilePath;

    public BookRepository(IBookParser bookParser, String stockFilePath) {
        _bookParser = bookParser;
        _stockFilePath = stockFilePath;
    }

    @Override
    public List<Book> GetAll() {
        var source = new File(_stockFilePath);

        return _bookParser.ParseFile(source);
    }

    @Override
    public Book Get(String id) {
        var books = GetAll();

        for (var book : books) {
            if (book.getBarcode() == id){
                return book;
            }
        }

        return null;
    }

    @Override
    public boolean Add(Book entity) {
        return false;
    }

    @Override
    public boolean Update(Book entity) {
        return false;
    }

    @Override
    public boolean Delete(Book entity) {
        return false;
    }

    @Override
    public boolean DeleteByBarcode(String barcode) {
        return false;
    }
}
