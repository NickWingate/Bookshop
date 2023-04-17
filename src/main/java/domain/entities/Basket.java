package main.java.domain.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Basket implements Iterable<Book>{

    private final List<Book> books = new ArrayList<Book>();
    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }

    public boolean addBook(Book book){
        // todo: should we store tuple with book quantity?
        return books.add(book);
    }

    public boolean removeBook(Book book){
        return books.remove(book);
    }

    public boolean removeBookByBarcode(String barcode){
        for (Book book :
                books) {
            if (book.getBarcode() == barcode)
            {
                removeBook(book);
                return true;
            }
        }

        return false;
    }

    public void clearBasket(){
        books.clear();
    }

    public double calculateTotal()
    {
        var total = 0;
        for (Book book : books) {
            total += book.getPrice();
        }

        return total;
    }

}
