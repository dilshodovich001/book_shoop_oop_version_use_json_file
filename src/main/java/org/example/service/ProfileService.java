package org.example.service;

import org.example.dto.ProfileRequest;
import org.example.entity.Profile;
import org.example.enums.GeneralStatus;
import org.example.exeptions.MethodNotAllowedException;
import org.example.exeptions.PasswordIsInValidException;
import org.example.exeptions.PhoneIsInValidException;
import org.example.repository.ProfileRepository;

public class ProfileService {
    private final ProfileRepository profileRepository = new ProfileRepository();

    public Profile login(String phone, String password) {
        checkProfile(phone, password, "name", 18);
        Profile profile = profileRepository.getProfile(phone, password);
        if (profile.getStatus().equals(GeneralStatus.BLOCKED) || profile.getStatus().equals(GeneralStatus.NOT_ACTIVE)) {
            throw new MethodNotAllowedException("MethodNotAllowedException");
        }
        return profile;
    }

    private void checkProfile(String phone, String password, String name, Integer age) {
        if (phone == null || phone.isBlank()) {
            throw new PhoneIsInValidException("Phone is invalid");
        }
        if (name == null || name.isBlank()) {
            throw new PhoneIsInValidException("Name is invalid");
        }
        if (password == null || password.isBlank()) {
            throw new PasswordIsInValidException("Phone is invalid");
        }
        if (age <= 0) {
            throw new PasswordIsInValidException("Age is invalid");
        }
    }

    public String register(ProfileRequest request) {
        checkProfile(request.phone(), request.password(), request.name(), request.age());
        Profile profile = new Profile(
                request.name(),
                request.phone(),
                request.password(),
                request.age()
        );
        profileRepository.save(profile);
        return "Successfully";
    }
}
