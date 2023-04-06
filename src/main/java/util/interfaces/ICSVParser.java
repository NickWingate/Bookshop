package main.java.util.interfaces;

import java.util.List;

public interface ICSVParser<T> {
    public List<T> ParseFile(String fileName);

    public T ParseLine(String line);
}
