package main.java.util.repositories;

import main.java.domain.entities.Book;
import main.java.util.interfaces.IBookParser;
import main.java.util.interfaces.IBookRepository;
import main.java.util.interfaces.ICSVWriter;

import java.io.File;
import java.util.List;

public class BookRepository implements IBookRepository {

    private IBookParser _bookParser;
    private ICSVWriter<Book> _bookWriter;
    private String _stockFilePath;

    public BookRepository(IBookParser bookParser,
                          ICSVWriter<Book> bookWriter,
                          String stockFilePath) {
        _bookParser = bookParser;
        _bookWriter = bookWriter;
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
        var books = GetAll();

        books.add(entity);

        return Save(books);
    }

    @Override
    public boolean Update(String id, Book entity) {
        var books = GetAll();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBarcode().equals(id)){
                books.set(i, entity);
            }
        }

        return Save(books);
    }

    @Override
    public boolean Delete(String id) {
        var books = GetAll();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBarcode().equals(id)){
                books.remove(i);
            }
        }

        return Save(books);
    }

    private boolean Save(List<Book> books) {
        return _bookWriter.WriteToFile(_stockFilePath, books);
    }
}
