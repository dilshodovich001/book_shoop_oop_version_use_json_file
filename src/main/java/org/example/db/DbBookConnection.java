package org.example.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.BookEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DbBookConnection {
    private File file;

    public DbBookConnection(String path) {
        this.file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<BookEntity> writeValue(List<BookEntity> data) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookEntity> response = readData();
        response.addAll(data);
        try {
            objectMapper.writeValue(this.file, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public List<BookEntity> readData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            try {
                if (!file.exists() || file.length() == 0) {
                    System.out.println("Warning: JSON file is empty or does not exist!");
                    return new ArrayList<>();
                }
                List<BookEntity> list = objectMapper.readValue(this.file, new TypeReference<List<BookEntity>>() {
                });
                if (list.isEmpty()) {
                    System.out.println("Maxgi");
                }
                return list;
            } catch (RuntimeException r) {
                return new LinkedList<>();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
