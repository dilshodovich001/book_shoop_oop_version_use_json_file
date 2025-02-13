package org.example.controller;

import org.example.dto.RegionRequest;
import org.example.dto.RegionResponse;
import org.example.dto.RegionShort;
import org.example.service.RegionService;

import java.util.List;

public class RegionController {
    private final RegionService regionService = new RegionService();
    public void createRegion(RegionRequest request) {
        regionService.createRegion(request);
    }

    public RegionResponse getId(String regionId) {
        return regionService.getId(regionId);
    }

    public boolean deleteRegion(String regionId) {
        return regionService.deleteGenre(regionId);
    }

    public void update(String id, RegionRequest request) {
        regionService.update(id,request);
    }

    public List<RegionShort> regionList(String language) {
        return regionService.regionList(language);
    }
}
