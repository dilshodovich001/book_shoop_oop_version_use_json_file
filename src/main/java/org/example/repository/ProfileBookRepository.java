package org.example.repository;

import org.example.db.DbProfileBookConnection;
import org.example.entity.ProfileBookEntity;

import java.util.List;

public class ProfileBookRepository {
    private final DbProfileBookConnection profileBookConnection = new DbProfileBookConnection("profile_book.json");
    public void save(ProfileBookEntity profileBookEntity) {
        profileBookConnection.writeValue(List.of(profileBookEntity));
    }
}
