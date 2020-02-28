package service;

import author.Author;

import java.sql.*;
import java.util.ArrayList;

public class AuthorService {
    public static final AuthorService instance = new AuthorService();

    private AuthorService() {
    }

    public void create(Author author) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.USER)) {
            if (conn != null) {
                String query = "INSERT INTO Authors(name,surname) VALUES (?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSurname());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public ArrayList<Author> findAllAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        Author author = new Author();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.USER)) {
            if (conn != null) {
                String query = "SELECT * FROM Authors;";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    author.setName(resultSet.getString("name"));
                    author.setSurname(resultSet.getString("surname"));
                    authors.add(author);
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return authors;
    }

    public Author findAuthorById(int id) {
        Author author = new Author();
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.USER)) {
            if (conn != null) {
                String query = "SELECT * FROM Authors WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    author.setName(resultSet.getString("name"));
                    author.setSurname(resultSet.getString("surname"));
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return author;
    }

    public void update(int id, Author author) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.USER)) {
            if(conn != null){
                String query = "UPDATE Authors SET name = ?, surname = ? WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,author.getName());
                preparedStatement.setString(2,author.getSurname());
                preparedStatement.setInt(3,id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL, MariaDbConstant.USER, MariaDbConstant.USER)) {
            if(conn != null){
                String query = "DELETE FROM Authors WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
}
