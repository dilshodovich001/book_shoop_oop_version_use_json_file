package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.Profile;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
public class BookRequest {
    private String title;
    private String author;
    private Integer year;
    private Double price;
    private byte regionId;
    private Set<String> genres;
    private Profile profile;
}
