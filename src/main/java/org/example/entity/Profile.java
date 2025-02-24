package org.example.entity;

import lombok.*;
import org.example.enums.ProfileRole;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Profile extends BaseEntity {
    private String name;
    private String phone;
    private String password;
    private Integer age;
    private Double balance;
    private ProfileRole role;

    public Profile(String name, String phone, String password, Integer age) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.age = age;
        this.balance = 0d;
        this.role = ProfileRole.USER;
    }
}
