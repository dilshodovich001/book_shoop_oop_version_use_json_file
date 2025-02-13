package org.example.service;

import org.example.dto.RegionRequest;
import org.example.dto.RegionResponse;
import org.example.dto.RegionShort;
import org.example.entity.RegionEntity;
import org.example.enums.Language;
import org.example.exeptions.RegionException;
import org.example.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class RegionService {
    private final RegionRepository regionRepository = new RegionRepository();

    public void createRegion(RegionRequest request) {
        if (request.nameRu() == null || request.nameRu().isBlank()) {
            throw new RegionException("GenreEntity Incorrect NameRu");
        }
        if (request.nameUz() == null || request.nameUz().isBlank()) {
            throw new RegionException("GenreEntity Incorrect NameUz");
        }
        if (request.nameEn() == null || request.nameEn().isBlank()) {
            throw new RegionException("GenreEntity Incorrect NameEn");
        }
        RegionEntity regionEntity = new RegionEntity(request.nameUz(), request.nameEn(), request.nameRu());
        regionRepository.save(regionEntity);
    }

    public RegionResponse getId(String id) {
        if (id == null) {
            throw new RegionException("ID null");
        }
        RegionEntity regionEntity = regionRepository.getId(id);
        if (regionEntity == null) {
            throw new RegionException("GenreEntity not Found");
        }
        return mapToResponse().apply(regionEntity);
    }

    public Function<RegionEntity, RegionResponse> mapToResponse() {
        return region -> new RegionResponse(
                region.getId(),
                region.getNameUz(),
                region.getNameRu(),
                region.getNameEng()
        );
    }

    public boolean deleteGenre(String regionId) {
        RegionEntity response = regionRepository.getId(regionId);
        if (response == null) {
            throw new RegionException("GenreEntity Not Found");
        }
        return regionRepository.delete(response);
    }

    public void update(String id, RegionRequest request) {
        if (id.isBlank()) {
            throw new RegionException("Id is empty");
        }
        RegionEntity entity = regionRepository.getId(id);
        entity.setId(UUID.fromString(id));
        entity.setNameRu(request.nameRu());
        entity.setNameEng(request.nameEn());
        entity.setNameUz(request.nameUz());
        regionRepository.update(entity);
    }

    public List<RegionShort> regionList(String language) {
        Language lang = Language.valueOf(language);
        List<RegionEntity> entities = regionRepository.regionList();
        List<RegionShort> regionResponses = new ArrayList<>();
        int id = 1;
        for (RegionEntity entity : entities) {
            RegionShort response = new RegionShort(
                    id++,
                    switch (lang) {
                        case en -> entity.getNameEng();
                        case ru -> entity.getNameRu();
                        default -> entity.getNameUz();
                    }
            );
            regionResponses.add(response);
        }
        return regionResponses;
    }

    public List<RegionEntity> getById(){
        return regionRepository.regionList();
    }
}
