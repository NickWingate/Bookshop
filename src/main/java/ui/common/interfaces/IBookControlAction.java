package main.java.ui.common.interfaces;

import main.java.domain.entities.Book;

@FunctionalInterface
public interface IBookControlAction {
    void onEvent(Book book, int quantity);
}
