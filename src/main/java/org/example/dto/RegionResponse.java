package org.example.dto;

import java.util.UUID;

public record RegionResponse(
        UUID id,
        String nameUz,
        String nameRu,
        String nameEn
) {
}
