package main.java.util.interfaces;

import java.util.List;

public interface ICSVEncoder<T> {
    public String EncodeObject(T object);

    default public String EncodeObjects(List<T> objects) {
        var string = "";

        for (var object : objects) {
            string += EncodeObject(object);
        }

        return string;
    }
}
