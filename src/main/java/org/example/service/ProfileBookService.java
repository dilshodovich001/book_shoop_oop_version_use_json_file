package org.example.service;

import org.example.entity.BookEntity;
import org.example.entity.Profile;
import org.example.entity.ProfileBookEntity;
import org.example.repository.ProfileBookRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProfileBookService {
    private final ProfileBookRepository profileBookRepository = new ProfileBookRepository();

//    private final BookService bookService = new BookService();

    public void buyBook(BookEntity book, Profile profile) {
        ProfileBookEntity profileBookEntity = new ProfileBookEntity();
        profileBookEntity.setId(UUID.randomUUID());
        profileBookEntity.setProfile(profile);
        profileBookEntity.setBook(book);
        profileBookEntity.setCreatedDate(LocalDateTime.now().toString());
        profileBookRepository.save(profileBookEntity);
    }

   /* public BookEntity getBook(UUID uuid) {
        return bookService.getBook(uuid);
    }*/
}
