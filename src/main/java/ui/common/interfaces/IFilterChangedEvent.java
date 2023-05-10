package main.java.ui.common.interfaces;

import main.java.domain.entities.Filter;
import main.java.domain.enums.IBookProperty;

import java.util.List;

@FunctionalInterface
public interface IFilterChangedEvent {
    void onChange(List<IBookProperty> selectedProperties);
}
