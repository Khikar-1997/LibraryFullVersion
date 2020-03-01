package controller;

import book.Book;
import service.BookService;

import java.util.Scanner;

public class BookController {
    private static final BookController instance = new BookController();
    private BookService bookService = BookService.instance;
    private Scanner scanner = new Scanner(System.in);

    private BookController() {
    }

    private void createBook() {
        System.out.println("Please write book name");
        String name = scanner.nextLine();
        System.out.println("Please write book description");
        String description = scanner.nextLine();
        System.out.println("Please write book page numbers");
        int pageNumbers = scanner.nextInt();
        String test = scanner.nextLine();
        System.out.println("Please write book state");
        String state = scanner.nextLine();
        bookService.create(new Book(name, description, pageNumbers, state));
    }

    private void selectAllBooks() {
        for (Book book : bookService.findAllBooks()) {
            System.out.println("name :" + book.getName() + " ," + "description :" + book.getDescription() +
                    " ," + "number of pages :" + book.getNumberOfPages() + " ," + "state :" +
                    book.getState());
        }

    }

    private void selectBook() {
        System.out.println("Please write book id");
        int id = scanner.nextInt();
        System.out.println(bookService.findBookById(id));
    }

    private void updateBook() {
        System.out.println("Please write book id");
        int id = scanner.nextInt();
        System.out.println("Please write book new name");
        String name = scanner.nextLine();
        System.out.println("Please write book new description");
        String description = scanner.nextLine();
        System.out.println("Please write book new page numbers");
        int pageNumbers = scanner.nextInt();
        String test = scanner.nextLine();
        System.out.println("Please write book new state");
        String state = scanner.nextLine();
        bookService.update(id, new Book(name, description, pageNumbers, state));
    }

    private void deleteBook() {
        System.out.println("Please write book id");
        int id = scanner.nextInt();
        bookService.delete(id);
    }

    public static void booksMenu() {
        BookController bookController = BookController.instance;
        int num;
        do {
            System.out.println("______________________________");
            System.out.println("              Books           ");
            System.out.println("   Press 1 to create book     ");
            System.out.println("   Press 2 to select all books");
            System.out.println("   Press 3 to select book     ");
            System.out.println("   Press 4 to update book     ");
            System.out.println("   Press 5 to delete book     ");
            System.out.println("   Press 6 to return menu     ");
            System.out.println("______________________________");

            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            String test = scanner.nextLine();
            switch (num) {
                case 1:
                    bookController.createBook();
                    break;
                case 2:
                    bookController.selectAllBooks();
                    break;
                case 3:
                    bookController.selectBook();
                    break;
                case 4:
                    bookController.updateBook();
                    break;
                case 5:
                    bookController.deleteBook();
                    break;
                case 6:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Plese write valid order");
            }
        } while (num != 6);
    }
}
