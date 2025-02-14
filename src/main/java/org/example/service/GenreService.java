package org.example.service;

import org.example.dto.GenreRequest;
import org.example.dto.GenreResponse;
import org.example.entity.GenreEntity;
import org.example.enums.Language;
import org.example.exeptions.GenreException;
import org.example.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class GenreService {
    private final GenreRepository genreRepository = new GenreRepository();

    public void createGenre(GenreRequest request) {
        if (request.nameRu() == null || request.nameRu().isBlank()) {
            throw new GenreException("GenreEntity Incorrect NameRu");
        }
        if (request.nameUz() == null || request.nameUz().isBlank()) {
            throw new GenreException("GenreEntity Incorrect NameUz");
        }
        if (request.nameEn() == null || request.nameEn().isBlank()) {
            throw new GenreException("GenreEntity Incorrect NameEn");
        }
        GenreEntity genreEntity = new GenreEntity(request.nameUz(), request.nameEn(), request.nameRu());
        genreRepository.save(genreEntity);
    }

    public GenreResponse getId(String id) {
        if (id == null) {
            throw new GenreException("ID null");
        }
        GenreEntity genreEntity = genreRepository.getId(id);
        if (genreEntity == null) {
            throw new GenreException("GenreEntity not Found");
        }
        return mapToResponse().apply(genreEntity);
    }

    public Function<GenreEntity, GenreResponse> mapToResponse() {
        return genreEntity -> new GenreResponse(
                genreEntity.getId(),
                genreEntity.getNameUz(),
                genreEntity.getNameRu(),
                genreEntity.getNameEng()
        );
    }

    public boolean deleteGenre(String genreId) {
        GenreEntity response = genreRepository.getId(genreId);
        if (response == null) {
            throw new GenreException("GenreEntity Not Found");
        }
        boolean t = genreRepository.delete(response);
        return t;
    }

    public void update(String id, GenreRequest request) {
        if (id.isBlank()) {
            throw new GenreException("Id is empty");
        }
        GenreEntity entity = genreRepository.getId(id);
        entity.setId(UUID.fromString(id));
        entity.setNameRu(request.nameRu());
        entity.setNameEng(request.nameEn());
        entity.setNameUz(request.nameUz());
        genreRepository.update(entity);
    }

    public GenreEntity getGenreById(String id) {
        return genreRepository.getId(id);
    }

    public List<GenreResponse.GenreLang> getGenByLang(Language language, List<GenreEntity> genreEntities) {
        List<GenreResponse.GenreLang> list = new ArrayList<>();
        for (GenreEntity genreEntity : genreEntities) {
            GenreResponse.GenreLang genreLang = new GenreResponse.GenreLang(
                    genreEntity.getId(),
                    switch (language) {
                        case en -> genreEntity.getNameEng();
                        case ru -> genreEntity.getNameRu();
                        default -> genreEntity.getNameUz();
                    }
            );
            list.add(genreLang);
        }
        return list;
    }
}
