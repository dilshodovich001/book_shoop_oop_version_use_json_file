package org.example.service;

import org.example.dto.BookRequest;
import org.example.dto.BookResponse;
import org.example.entity.BookEntity;
import org.example.entity.GenreEntity;
import org.example.entity.RegionEntity;
import org.example.enums.Language;
import org.example.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();
    private final GenreService genreService = new GenreService();
    private final RegionService regionService = new RegionService();
    private final ProfileService profileService = new ProfileService();

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

    public List<BookResponse> showBook(String lan) {
        List<BookResponse> responses = new ArrayList<>();
        List<BookEntity> entities = bookRepository.getData();
        for (BookEntity entity : entities) {
            BookResponse response = new BookResponse();
            response.setAuthor(entity.getAuthorName());
            response.setTitle(entity.getTitle());
            response.setYear(entity.getYear());
            response.setPrice(entity.getPrice());
            response.setRegion(regionService.getRegionByLang(Language.valueOf(lan), entity.getRegionEntity()));
            response.setGenres(genreService.getGenByLang(Language.valueOf(lan), entity.getGenreEntities()));
            response.setProfile(profileService.getProfileMapper(entity.getProfile()));
            responses.add(response);
        }
        return responses;
    }

    public boolean deleteBook(UUID uuid) {
        List<BookEntity> data = bookRepository.getData();
        Optional<BookEntity> response = data
                .stream()
                .filter(bookEntity -> bookEntity.getId().equals(uuid)).findFirst();
        data.removeIf(bookEntity -> bookEntity.getId().equals(uuid));
        if (response.isPresent()) {
            BookEntity entity = response.get();
            entity.setVisible(Boolean.FALSE);
            data.add(entity);
            bookRepository.saveAll(data);
        }
        return true;
    }
}
