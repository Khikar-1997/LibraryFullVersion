package database;

import service.MariaDbConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    public static Database instance = new Database();

    private Database() {
    }


    private void createDatabase() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL_SERVER, MariaDbConstant.USER, MariaDbConstant.PASS)) {

                String query = "CREATE DATABASE library;";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createBooksTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {

                String query =
                        "CREATE TABLE books" +
                        "(" +
                        "id INT NOT NULL AUTO_INCREMENT ," +
                        "name VARCHAR(255) NOT NULL ," +
                        "description VARCHAR(255) NOT NULL ," +
                        "number_of_pages INT NOT NULL ," +
                        "state ENUM ('NOT_TAKEN','TAKEN') ," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.execute();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createAuthorsTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {

                String query =
                        "CREATE TABLE authors" +
                        "(" +
                        "id INT NOT NULL AUTO_INCREMENT ," +
                        "name VARCHAR(255) NOT NULL ," +
                        "surname VARCHAR(255) NOT NULL ," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.execute();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createCustomersTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {

                String query =
                        "CREATE TABLE customers" +
                        "(" +
                        "id INT NOT NULL AUTO_INCREMENT ," +
                        "name VARCHAR(255) NOT NULL ," +
                        "surname VARCHAR(255) NOT NULL ," +
                        "password VARCHAR(255) NOT NULL ," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.execute();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createOrderTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {

                String query =
                        "CREATE TABLE Orders" +
                        "(" +
                        "id INT NOT NULL AUTO_INCREMENT ," +
                        "return_date DATE," +
                        "customer_id INT UNIQUE ," +
                        "book_id INT UNIQUE ," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE ," +
                        "FOREIGN KEY (book_id) REFERENCES  books(id) ON DELETE CASCADE " +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.execute();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createTableBooksAndAuthors() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {

                String query =
                        "CREATE TABLE Books_and_authors" +
                        "(" +
                        "id INT NOT NULL AUTO_INCREMENT," +
                        "book_id INT," +
                        "author_id INT," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE ," +
                        "FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE " +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.execute();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    private void createArchiveTable() {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.PASS)) {

                String query =
                        "CREATE TABLE Archive" +
                        "(" +
                        "id INT NOT NULL AUTO_INCREMENT ," +
                        "customer_id INT UNIQUE ," +
                        "book_id INT ," +
                        "PRIMARY KEY (id)," +
                        "FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE ," +
                        "FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE " +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.execute();

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public static void initializieDatabase() {
        Database database = Database.instance;
        database.createDatabase();
        database.createBooksTable();
        database.createAuthorsTable();
        database.createCustomersTable();
        database.createOrderTable();
        database.createTableBooksAndAuthors();
        database.createArchiveTable();
    }
}
