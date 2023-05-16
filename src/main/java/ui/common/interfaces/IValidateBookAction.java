package main.java.ui.common.interfaces;

import main.java.domain.entities.Book;

import java.util.List;

@FunctionalInterface
public interface IValidateBookAction {
    List<String> validate(Book book);
}
