package main.java.util.file;

import main.java.util.interfaces.ICSVEncoder;
import main.java.util.interfaces.ICSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter<T> implements ICSVWriter<T> {

    private ICSVEncoder<T> _csvEncoder;
    public CSVWriter(ICSVEncoder<T> csvEncoder) {
        _csvEncoder = csvEncoder;
    }

    @Override
    public void WriteToFile(String filePath, List<T> entities) {
        var csvString = _csvEncoder.EncodeObjects(entities);

        try {
            var writer = new FileWriter(filePath);
            writer.write(csvString);
            writer.close();
        } catch (IOException e) {
            System.out.println("Couldn't write to file");
            e.printStackTrace();
        }

    }
}
