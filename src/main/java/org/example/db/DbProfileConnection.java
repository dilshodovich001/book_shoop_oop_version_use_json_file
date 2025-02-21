package org.example.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Profile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DbProfileConnection {
    private File file;

    public DbProfileConnection(String path) {
        this.file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Profile> writeValue(List<Profile> data) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Profile> response = readData();
        response.addAll(data);
        try {
            objectMapper.writeValue(this.file, response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public List<Profile> readData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            try {
                if (!file.exists() || file.length() == 0) {
                    System.out.println("Warning: JSON file is empty or does not exist!");
                    return new ArrayList<>();
                }
                List<Profile> list = objectMapper.readValue(this.file, new TypeReference<List<Profile>>() {
                });
                if (list.isEmpty()){
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

    public void clear(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file,new ArrayList<>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
