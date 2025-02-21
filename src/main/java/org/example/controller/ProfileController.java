package org.example.controller;

import org.example.dto.ProfileRequest;
import org.example.entity.Profile;
import org.example.service.ProfileService;

public class ProfileController {
    private final ProfileService profileService = new ProfileService();

    public Profile login(String phone, String password) {
        return profileService.login(phone,password);
    }

    public String register(ProfileRequest request) {
        return profileService.register(request);
    }

    public String fillBalance(Profile profile, Double balance) {
        return profileService.fillBalance(profile,balance) ;
    }
}
