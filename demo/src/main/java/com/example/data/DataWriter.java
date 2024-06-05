package com.example.data;

import java.io.FileWriter;
import java.io.IOException;

public class DataWriter implements DataWriterInterface {
    private String filename;

    public DataWriter(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeToFile(String data) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
