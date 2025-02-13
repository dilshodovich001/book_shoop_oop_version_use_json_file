package org.example.ui;

import org.example.controller.GenreController;
import org.example.controller.RegionController;
import org.example.dto.*;
import org.example.util.ScannerUtil;

import java.util.List;

public class RegionUi {
    private final RegionController regionController = new RegionController();

    public void start() {
        while (true) {
            switch (menu()) {
                case 1 -> addRegion();
                case 2 -> editRegion();
                case 3 -> getRegion();
                case 4 -> deleteRegion();
                case 5 -> showRegion();
                case 0-> {
                    return;
                }
            }
        }
    }

    private void showRegion() {
        System.out.print("Enter lang (ru/en/uz) : ");
        String language = ScannerUtil.SCANNER_STR.next();
        List<RegionShort> regionResponses = regionController.regionList(language);
        regionResponses.forEach(System.out::println);
    }

    private void deleteRegion() {
        System.out.print("Enter id : ");
        String id = ScannerUtil.SCANNER_STR.next();
        boolean response = regionController.deleteRegion(id);
        System.out.println(response);
    }

    private void getRegion() {
        System.out.print("Enter id : ");
        String id = ScannerUtil.SCANNER_STR.next();
        RegionResponse response = regionController.getId(id);
        System.out.println(response);
    }

    private void editRegion() {
        System.out.print("Enter id : ");
        String id = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter genre uz : ");
        String nameUz = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter genre ru : ");
        String nameRu = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter genre en : ");
        String nameEn = ScannerUtil.SCANNER_STR.next();
        RegionRequest request = new RegionRequest(nameUz, nameRu, nameEn);
        regionController.update(id,request);
    }

    public void addRegion() {
        System.out.print("Enter region uz : ");
        String nameUz = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter region ru : ");
        String nameRu = ScannerUtil.SCANNER_STR.next();
        System.out.print("Enter region en : ");
        String nameEn = ScannerUtil.SCANNER_STR.next();
        RegionRequest request = new RegionRequest(nameUz, nameRu, nameEn);
        regionController.createRegion(request);
        System.out.println("Success");
    }

    public int menu() {
        System.out.println("*****REGION*****");
        System.out.println("1.Region Create");
        System.out.println("2.Region Update");
        System.out.println("3.Region Read");
        System.out.println("4.Region Delete");
        System.out.println("5.Region List");
        System.out.println("0.Exit");
        System.out.print("Enter menu : ");
        return ScannerUtil.SCANNER_NUM.nextInt();
    }
}
