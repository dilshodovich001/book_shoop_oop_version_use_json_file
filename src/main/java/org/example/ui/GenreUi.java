package org.example.ui;

import org.example.controller.GenreController;
import org.example.dto.GenreRequest;
import org.example.dto.GenreResponse;
import org.example.util.ScannerUtil;

public class GenreUi {
    private final GenreController genreController = new GenreController();

    public void start() {
        while (true) {
            switch (menu()) {
                case 1 -> addGenre();
                case 2 -> editGenre();
                case 3 -> getGenre();
                case 4 -> getDelete();
                case 0-> {
                    return;
                }
            }
        }
    }

    private void getDelete() {
        System.out.print("Enter id : ");
        String id = ScannerUtil.SCANNER_STR.next();
        boolean response = genreController.deleteGenre(id);
        System.out.println(response);
    }

    private void getGenre() {
        System.out.print("Enter id : ");
        String id = ScannerUtil.SCANNER_STR.next();
        GenreResponse response = genreController.getId(id);
        System.out.println(response);
    }

    private void editGenre() {
        System.out.print("Enter id : ");
        String id = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter genre uz : ");
        String nameUz = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter genre ru : ");
        String nameRu = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter genre en : ");
        String nameEn = ScannerUtil.SCANNER_STR.next();
        GenreRequest request = new GenreRequest(nameUz, nameRu, nameEn);
        genreController.update(id,request);
    }

    public void addGenre() {
        System.out.print("Enter genre uz : ");
        String nameUz = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter genre ru : ");
        String nameRu = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter genre en : ");
        String nameEn = ScannerUtil.SCANNER_STR.next();
        GenreRequest request = new GenreRequest(nameUz, nameRu, nameEn);
        genreController.createGenre(request);
        System.out.println("Success");
    }

    public int menu() {
        System.out.println("*****GENRE*****");
        System.out.println("1.Genre Create");
        System.out.println("2.Genre Update");
        System.out.println("3.Genre Read");
        System.out.println("4.Genre Delete");
        System.out.println("0.Exit");
        System.out.print("Enter menu : ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
