package main.java.util.interfaces;

import java.io.File;
import java.util.List;

public interface ICSVParser<T> {
    public List<T> ParseFile(File source);

    public T ParseLine(String line);
}
