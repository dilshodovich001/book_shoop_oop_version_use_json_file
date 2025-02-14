package org.example.dto;

import java.util.UUID;

public record ProfileResponse(
        UUID id,
        String name,
        String phone
) {
}
