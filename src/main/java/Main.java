import database.Database;
import manu.LibraryManu;

public class Main {
    public static void main(String[] args) {
        Database.initializieDatabase();
        LibraryManu.menu();
    }
}
