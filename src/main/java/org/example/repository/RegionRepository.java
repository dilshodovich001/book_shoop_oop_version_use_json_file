package org.example.repository;

import org.example.db.DbRegionConnection;
import org.example.entity.RegionEntity;

import java.util.List;

public class RegionRepository {
    private final DbRegionConnection dbRegionConnection = new DbRegionConnection("region.json");

    public void save(RegionEntity regionEntity) {
        dbRegionConnection.writeValue(List.of(regionEntity));
    }

    public RegionEntity getId(String id) {
        for (RegionEntity regionEntity : dbRegionConnection.readData()) {
            if (regionEntity.getId().toString().equals(id)) {
                return regionEntity;
            }
        }
        return null;
    }

    public boolean delete(RegionEntity response) {
        List<RegionEntity> genreEntities = dbRegionConnection.readData();
        genreEntities.removeIf(regionEntity -> regionEntity.getId().equals(response.getId()));
        dbRegionConnection.clear();
        dbRegionConnection.writeValue(genreEntities);
        return true;
    }

    public void update(RegionEntity entity) {
        List<RegionEntity> regionEntities = dbRegionConnection.readData();
        regionEntities.removeIf(genre-> genre.getId().equals(entity.getId()));
        dbRegionConnection.clear();
        regionEntities.add(entity);
        dbRegionConnection.writeValue(regionEntities);
    }

    public List<RegionEntity> regionList() {
        return dbRegionConnection.readData();
    }
}
