package main.java.util.interfaces;

import main.java.domain.entities.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface ICSVParser<T> {
    default public List<T> ParseFile(File source){
        var entities = new ArrayList<T>();

        try {
            var scanner = new Scanner(source);
            while (scanner.hasNextLine()){
                var book = ParseLine(scanner.nextLine());

                entities.add(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return entities;
    }

    public T ParseLine(String line);
}
