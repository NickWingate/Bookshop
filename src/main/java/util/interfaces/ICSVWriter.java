package main.java.util.interfaces;

import java.io.File;
import java.util.List;

public interface ICSVWriter<T> {
    public boolean WriteToFile(String filePath, List<T> entities);
}
