package org.example.service;

import org.example.dto.BookRequest;
import org.example.entity.BookEntity;
import org.example.entity.GenreEntity;
import org.example.entity.RegionEntity;
import org.example.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();
    private final GenreService genreService = new GenreService();
    private final RegionService regionService = new RegionService();

    public void addBook(BookRequest request) {
        List<GenreEntity> genreEntities = new ArrayList<>();
        for (String genreId : request.getGenres()) {
            genreEntities.add(genreService.getGenreById(genreId));
        }
        List<RegionEntity> regions = regionService.getById();
        RegionEntity regionEntity = regions.get(request.getRegionId() - 1);

        BookEntity entity = new BookEntity();
        entity.setId(UUID.randomUUID());
        entity.setTitle(request.getTitle());
        entity.setAuthorName(request.getAuthor());
        entity.setPrice(request.getPrice());
        entity.setYear(request.getYear());
        entity.setProfile(request.getProfile());
        entity.setGenreEntities(genreEntities);
        entity.setRegionEntity(regionEntity);
        bookRepository.save(entity);
    }
}
