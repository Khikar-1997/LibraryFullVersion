package controller;

import service.LibraryService;

import java.util.Scanner;

public class LibraryController {
    static LibraryController instance = new LibraryController();

    private LibraryController() {
    }

    public static void findAllBooksByState(){
        LibraryService libraryService = LibraryService.instance;
        String state = "NOT_TAKEN";
        System.out.println(libraryService.findAllBooksByState(state));
    }

    public static void takeBook() {
        LibraryService libraryService = LibraryService.instance;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write book id");
        int bookId = scanner.nextInt();
        if (libraryService.testState(bookId)) {
            System.out.println("Please write customer id");
            int customerId = scanner.nextInt();
            libraryService.takeBook(customerId, bookId);
        } else {
            System.out.println("At this moment this book has taken");
        }
    }

    public static void returnBook() {
        LibraryService libraryService = LibraryService.instance;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write book id");
        int bookId = scanner.nextInt();
        System.out.println("Plese write customer id");
        int customerId = scanner.nextInt();
        libraryService.returnBook(bookId, customerId);
    }
}
