package org.example.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity extends BaseEntity{
    private String title;
    private String authorName;
    private Integer year;
    private Double price;
    private RegionEntity regionEntity;
    private List<GenreEntity> genreEntities;
    private Profile profile;
}
