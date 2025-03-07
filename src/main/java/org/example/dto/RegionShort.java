package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
public class RegionShort {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return id + ", " + name;
    }
}

