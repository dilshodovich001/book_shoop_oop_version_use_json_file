package org.example.dto;

import java.util.UUID;

public record GenreResponse(
        UUID id,
        String nameUz,
        String nameRu,
        String nameEn
) {
}
