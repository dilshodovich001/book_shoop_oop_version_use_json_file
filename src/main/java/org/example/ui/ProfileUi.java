package org.example.ui;

import org.example.controller.ProfileController;
import org.example.dto.ProfileRequest;
import org.example.entity.Profile;
import org.example.enums.ProfileRole;
import org.example.util.ScannerUtil;

public class ProfileUi {
    private final ProfileController profileController = new ProfileController();
    private final GenreUi genreUi = new GenreUi();
    private final BookUi bookUi = new BookUi();
    private final RegionUi regionUi = new RegionUi();

    public void login(String phone, String password) {
        Profile profile = profileController.login(phone, password);
        if (profile.getRole().equals(ProfileRole.ADMIN)) {
            adminStart(profile);
        } else if (profile.getRole().equals(ProfileRole.USER)) {
            userStart(profile);
        }
    }

    private void userStart(Profile profile) {
        while (true) {
            switch (userMenu()) {
                case 1 -> searchBook();
                case 2 -> showBook();
                case 3 -> buyBook(profile);
                case 4 -> showOwnSales(profile);
                case 5 -> changePassword(profile);
                case 6 -> fillBalance(profile);
            }
        }
    }

    private void fillBalance(Profile profile) {
        System.out.print("Enter money: ");
        Double balance = ScannerUtil.SCANNER_NUM.nextDouble();
        String response = profileController.fillBalance(profile, balance);
        System.out.println(response);
    }

    private void changePassword(Profile profile) {

    }

    private void showOwnSales(Profile profile) {

    }

    private void buyBook(Profile profile) {

    }

    private void showBook() {

    }

    private void searchBook() {

    }

    private void adminStart(Profile profile) {
        while (true) {
            switch (menu()) {
                case 1 -> bookUi.start(profile);
                case 2 -> genreUi.start();
                case 3 -> regionUi.start();
                case 0 -> {
                    return;
                }
            }
        }
    }

    public String register(ProfileRequest request) {
        return profileController.register(request);
    }

    private int menu() {
        System.out.print("""
                +------  MENU  ------+
                | 1 | Book Section   |
                | 2 | Genre Section  |
                | 3 | Region Section |
                | 4 | Show Profiles  |
                | 5 | Sale History   |
                +--------------------+
                | 0 | Exit           |
                +--------------------+
                | Enter Option ->""");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }

    private int userMenu() {
        System.out.print("""
                +------  MENU  -------+
                | 1 | Search Book     |
                | 2 | Show Book       |
                | 3 | Buy Book        |
                | 4 | Show own Sales  |
                | 5 | Change Password |
                | 6 | Fill Balance    |
                +---------------------+
                | 0 | Exit            |
                +---------------------+
                | Enter Option ->""");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }

}
