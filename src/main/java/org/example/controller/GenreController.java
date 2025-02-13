package org.example.controller;

import org.example.dto.GenreRequest;
import org.example.dto.GenreResponse;
import org.example.service.GenreService;

public class GenreController {
    private final GenreService genreService = new GenreService();
    public void createGenre(GenreRequest request) {
        genreService.createGenre(request);
    }

    public GenreResponse getId(String genreId) {
        return genreService.getId(genreId);
    }

    public boolean deleteGenre(String genreId) {
        return genreService.deleteGenre(genreId);
    }

    public void update(String id, GenreRequest request) {
        genreService.update(id,request);
    }
}
