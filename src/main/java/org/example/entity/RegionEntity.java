package org.example.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegionEntity {
    private UUID id;
    private String nameUz;
    private String nameEng;
    private String nameRu;

    public RegionEntity(String nameUz, String nameEng, String nameRu) {
        this.nameUz = nameUz;
        this.nameEng = nameEng;
        this.nameRu = nameRu;
        this.id = UUID.randomUUID();
    }
}
