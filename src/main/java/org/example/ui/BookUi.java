package org.example.ui;

import org.example.controller.BookController;
import org.example.dto.BookRequest;
import org.example.dto.BookResponse;
import org.example.dto.RegionResponse;
import org.example.dto.RegionShort;
import org.example.entity.Profile;
import org.example.service.RegionService;
import org.example.util.ScannerUtil;

import java.util.*;

public class BookUi {
    private final BookController bookController = new BookController();
    private final RegionService regionService = new RegionService();

    public void start(Profile profile) {
        while (true) {
            switch (menu()) {
                case 1 -> addBook(profile);
                case 2 -> showBook();
                case 3 -> editBook(profile);
                case 4 -> deleteBook(profile);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void deleteBook(Profile profile) {

    }

    private void editBook(Profile profile) {

    }

    private void showBook() {
        System.out.print("Enter lang (ru/en/uz): ");
        String lan = ScannerUtil.SCANNER_STR.next().toLowerCase();
        List<BookResponse> responses = bookController.showBook(lan);
        responses.forEach(System.out::println);
    }

    private void addBook(Profile profile) {
        System.out.print("Enter title :");
        String title = new Scanner(System.in).nextLine();
        System.out.print("Enter author name :");
        String author = new Scanner(System.in).nextLine();
        System.out.print("Enter year : ");
        Integer year = ScannerUtil.SCANNER_NUM.nextInt();
        System.out.print("Enter price : ");
        Double price = ScannerUtil.SCANNER_NUM.nextDouble();
        System.out.print("Enter lang (ru/en/uz) : ");
        String language = ScannerUtil.SCANNER_STR.next();
        List<RegionShort> regionShorts = regionService.regionList(language);
        regionShorts.forEach(System.out::println);
        System.out.print("Enter id : ");
        byte regionId = ScannerUtil.SCANNER_NUM.nextByte();
        System.out.print("Enter genre count : ");
        byte genreCount = ScannerUtil.SCANNER_NUM.nextByte();
        Set<String> genres = new HashSet<>();
        for (int i = 0; i < genreCount; i++) {
            System.out.print("Enter genre id : ");
            String genreId = ScannerUtil.SCANNER_STR.next();
            genres.add(genreId);
        }
        BookRequest request = new BookRequest(title, author, year, price, regionId, genres, profile);
        bookController.addBook(request);
    }

    private int menu() {
        System.out.print("""
                +------  MENU  ------+
                | 1 | Add Book       |
                | 2 | Show Book      |
                | 3 | Edit Book      |
                | 4 | Delete Book    |
                +--------------------+
                | 0 | Exit           |
                +--------------------+
                | Enter Option ->""");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
