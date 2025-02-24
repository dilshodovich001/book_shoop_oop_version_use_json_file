package org.example.service;

import org.example.db.DbBookConnection;
import org.example.dto.ProfileRequest;
import org.example.dto.ProfileResponse;
import org.example.entity.BookEntity;
import org.example.entity.Profile;
import org.example.enums.GeneralStatus;
import org.example.exeptions.MethodNotAllowedException;
import org.example.exeptions.PasswordIsInValidException;
import org.example.exeptions.PhoneIsInValidException;
import org.example.repository.BookRepository;
import org.example.repository.ProfileRepository;

import java.util.Optional;
import java.util.UUID;

public class ProfileService {
    private final ProfileRepository profileRepository = new ProfileRepository();
    private final BookRepository bookRepository = new BookRepository();
    private final ProfileBookService profileBookService = new ProfileBookService();

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

    public ProfileResponse getProfileMapper(Profile profile) {
        return new ProfileResponse(profile.getId(), profile.getName(), profile.getPhone());
    }

    public String fillBalance(Profile profile, Double balance) {
        Profile entity = profileRepository.getProfile(profile.getPhone(), profile.getPassword());
        entity.setBalance(entity.getBalance() + balance);
        profileRepository.fillBalance(entity);
        return "Success";
    }

    public String buyBook(UUID uuid, Profile profile) {
        Optional<BookEntity> first = bookRepository.getData().stream().filter(bookEntity -> bookEntity.getId().equals(uuid)).findFirst();
        if (first.isEmpty()) {
            throw new RuntimeException();
        }
        if (first.get().getPrice() > profile.getBalance()) {
            throw new RuntimeException("Not money");
        }
        profile.setBalance(profile.getBalance()-first.get().getPrice());
        profileRepository.fillBalance(profile);
        profileBookService.buyBook(first.get(), profile);
        return "Success";
    }
}
