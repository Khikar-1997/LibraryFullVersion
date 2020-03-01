package controller;

import author.Author;
import service.AuthorService;

import java.util.Scanner;

public class AuthorController {
    private static final AuthorController instance = new AuthorController();
    private AuthorService authorService = AuthorService.instance;
    private Scanner scanner = new Scanner(System.in);

    private AuthorController(){
    }

    private void createAuthor() {
        System.out.println("Please write author name");
        String name = scanner.nextLine();
        System.out.println("Please write author surname");
        String surname = scanner.nextLine();
        authorService.create(new Author(name,surname));
    }

    private void selectAllAuthors() {
        for (Author author : authorService.findAllAuthors()){
            System.out.println("name :" + author.getName() + " ," + "surname :" +author.getSurname());
        }
    }

    private void selectAuthor(){
        System.out.println("Please write author id");
        int id = scanner.nextInt();
        System.out.println(authorService.findAuthorById(id));
    }

    private void updateAuthor() {
        System.out.println("Please write author id");
        int id = scanner.nextInt();
        System.out.println("Plese write author new name");
        String name = scanner.nextLine();
        System.out.println("Please write author new surname");
        String surname = scanner.nextLine();
        authorService.update(id,new Author(name,surname));
    }

    private void deleteAuthor() {
        System.out.println("Please write author id");
        int id = scanner.nextInt();
        authorService.delete(id);
    }

    public static void authorsMenu() {
        AuthorController authorController = AuthorController.instance;
        int num;
        do {
            System.out.println("________________________________");
            System.out.println("             Authors            ");
            System.out.println("   Press 1 to create author     ");
            System.out.println("   Press 2 to select all authors");
            System.out.println("   Press 3 to select author     ");
            System.out.println("   Press 4 to update author     ");
            System.out.println("   Press 5 to delete author     ");
            System.out.println("   Press 6 to return menu       ");
            System.out.println("________________________________");

            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            String test = scanner.nextLine();
            switch (num) {
                case 1:
                    authorController.createAuthor();
                    break;
                case 2:
                    authorController.selectAllAuthors();
                    break;
                case 3:
                    authorController.selectAuthor();
                    break;
                case 4:
                    authorController.updateAuthor();
                    break;
                case 5:
                    authorController.deleteAuthor();
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
