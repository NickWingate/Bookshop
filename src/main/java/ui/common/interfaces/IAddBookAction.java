package main.java.ui.common.interfaces;

import main.java.domain.entities.Book;

@FunctionalInterface
public interface IAddBookAction {
    void Add(Book book);
}
