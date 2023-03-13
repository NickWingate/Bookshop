package Common.Interfaces;

import Entities.Book;

public interface IBookRepository extends IBaseRepository<Book> {
    public boolean DeleteByBarcode(String barcode);
}
