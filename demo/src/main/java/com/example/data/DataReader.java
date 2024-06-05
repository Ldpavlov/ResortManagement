package com.example.data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataReader implements DataReaderInterface {

    private String filename;
    private FileReader fileReader;
    private int ch;

    public DataReader(String filename) {
        this.filename = filename;
    }

    @Override
    public void readFile(String data) throws IOException {
        try {
            fileReader = new FileReader(data);
        } catch (FileNotFoundException fe) {
            System.out.println("File not found");
        }

        while ((ch = fileReader.read()) != -1)
            System.out.print((char) ch);

        fileReader.close();

    }

}
