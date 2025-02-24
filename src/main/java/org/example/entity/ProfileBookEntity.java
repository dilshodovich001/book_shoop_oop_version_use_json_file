package org.example.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Getter
public class ProfileBookEntity {
    private UUID id;
    private Profile profile;
    private BookEntity book;
    private String createdDate;
}
