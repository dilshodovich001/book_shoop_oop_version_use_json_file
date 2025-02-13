package org.example.entity;

import com.sun.jdi.LocalVariable;
import lombok.*;
import org.example.enums.GeneralStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    private UUID id = UUID.randomUUID();
    private GeneralStatus status = GeneralStatus.ACTIVE;
    private Boolean visible = Boolean.TRUE;
    private String createdDate = String.valueOf(LocalDateTime.now());
}
