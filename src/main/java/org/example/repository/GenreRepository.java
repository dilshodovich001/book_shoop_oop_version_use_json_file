package org.example.repository;

import org.example.db.DbGenreConnection;
import org.example.dto.GenreResponse;
import org.example.entity.GenreEntity;

import java.util.List;

public class GenreRepository {
    private final DbGenreConnection dbGenreConnection = new DbGenreConnection("genre.json");

    public void save(GenreEntity genreEntity) {
        dbGenreConnection.writeValue(List.of(genreEntity));
    }

    public GenreEntity getId(String id) {
        for (GenreEntity genreEntity : dbGenreConnection.readData()) {
            if (genreEntity.getId().toString().equals(id)) {
                return genreEntity;
            }
        }
        return null;
    }

    public boolean delete(GenreEntity response) {
        List<GenreEntity> genreEntities = dbGenreConnection.readData();
        genreEntities.removeIf(genreEntity -> genreEntity.getId().equals(response.getId()));
        dbGenreConnection.clear();
        dbGenreConnection.writeValue(genreEntities);
        return true;
    }

    public void update(GenreEntity entity) {
        List<GenreEntity> genreEntities = dbGenreConnection.readData();
        genreEntities.removeIf(genre-> genre.getId().equals(entity.getId()));
        dbGenreConnection.clear();
        genreEntities.add(entity);
        dbGenreConnection.writeValue(genreEntities);
    }
}
