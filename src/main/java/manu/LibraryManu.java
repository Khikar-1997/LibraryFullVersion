package manu;

import controller.AuthorController;
import controller.BookController;
import controller.CustomerController;
import controller.LibraryController;

import java.util.Scanner;

public class LibraryManu {
    static LibraryManu instance = new LibraryManu();

    private LibraryManu(){
    }

    public static void menu(){
        int num;
        do {
            System.out.println("_____________________________________");
            System.out.println("                Menu                 ");
            System.out.println("   press 1 for books CRUD            ");
            System.out.println("   press 2 for authors CRUD          ");
            System.out.println("   press 3 for customers CRUD        ");
            System.out.println("   press 4 show all not taken books  ");
            System.out.println("   press 5 take book from library    ");
            System.out.println("   press 6 to return the book        ");
            System.out.println("   Press 7 to exit                   ");
            System.out.println("_____________________________________");

            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            String test = scanner.nextLine();
            switch (num) {
                case 1:
                    BookController.booksMenu();
                    break;
                case 2:
                    AuthorController.authorsMenu();
                    break;
                case 3:
                    CustomerController.customersMenu();
                    break;
                case 4:
                    LibraryController.findAllBooksByState();
                    break;
                case 5:
                    LibraryController.takeBook();
                    break;
                case 6:
                    LibraryController.returnBook();
                    break;
                case 7:
                    System.out.println("Goodbye ");
                    break;
                default:
                    System.out.println("Plese write valid order");
            }

        }while (num != 7);
    }
}
