package org.example.repository;

import org.example.db.DbProfileConnection;
import org.example.entity.Profile;

import java.util.List;
import java.util.Optional;

public class ProfileRepository {

    private final DbProfileConnection dbProfileConnection = new DbProfileConnection("profile.json");

    public Profile getProfile(String phone, String password) {
        List<Profile> profiles = dbProfileConnection.readData();
        for (Profile profile : profiles) {
            if (profile.getPhone().equals(phone)
                    && profile.getPassword().equals(password)) {
                return profile;
            }
        }
        return null;
    }

    public void save(Profile request) {
        dbProfileConnection.writeValue(List.of(request));
    }

    public void fillBalance(Profile entity) {
        List<Profile> profiles = dbProfileConnection.readData();
        profiles.removeIf(profile -> profile.getId().equals(entity.getId()));
        dbProfileConnection.clear();
        profiles.add(entity);
        dbProfileConnection.writeValue(profiles);
    }

    public Profile minusMoney(Profile profile) {
        List<Profile> profiles = dbProfileConnection.readData();
        Optional<Profile> first = profiles.stream().filter(temp -> temp.getId().equals(profile.getId())).findFirst();
        profiles.remove(profile);
        dbProfileConnection.clear();
        profiles.add(profile);
        return null;
    }
}
