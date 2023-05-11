package main.java.util.file;

import main.java.util.interfaces.ICSVEncoder;
import main.java.util.interfaces.ICSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CSVWriter<T> implements ICSVWriter<T> {

    private ICSVEncoder<T> _csvEncoder;

    @Autowired
    public CSVWriter(ICSVEncoder<T> csvEncoder) {
        _csvEncoder = csvEncoder;
    }

    @Override
    public boolean WriteToFile(String filePath, List<T> entities) {
        var csvString = _csvEncoder.EncodeObjects(entities);

        try {
            var writer = new FileWriter(filePath);
            writer.write(csvString);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }

    }
}
