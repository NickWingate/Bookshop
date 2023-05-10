package main.java.domain.entities;

import java.util.*;


public class Basket implements Iterable<Map.Entry<Book, Integer>> {

    private final Map<Book, Integer> books = new HashMap<>();

    public void removeBook(Book book){
        books.remove(book);
    }

    public boolean removeBookByBarcode(String barcode){
        for (Book book : books.keySet()) {
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
        for (var kvp : books.entrySet()) {
            total += kvp.getKey().getPrice() * kvp.getValue();
        }

        return total;
    }

    @Override
    public Iterator<Map.Entry<Book, Integer>> iterator() {
        return books.entrySet().iterator();
    }
}
