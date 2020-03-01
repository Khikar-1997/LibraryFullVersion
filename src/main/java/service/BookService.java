package service;

import book.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookService {
    public static final BookService instance = new BookService();

    private BookService(){
    }

    public void create(Book book){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if(conn != null){
                String query = "INSERT INTO books(name,description,number_of_pages,state) VALUES(?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,book.getName());
                preparedStatement.setString(2,book.getDescription());
                preparedStatement.setInt(3,book.getNumberOfPages());
                preparedStatement.setString(4,book.getState());
                preparedStatement.execute();
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
    }

    public ArrayList<Book> findAllBooks(){
        ArrayList<Book> books = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if (conn != null){
                String query = "SELECT * FROM books";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Book book = new Book();
                    book.setName(resultSet.getString("name"));
                    book.setDescription(resultSet.getString("description"));
                    book.setNumberOfPages(resultSet.getInt("number_of_pages"));
                    book.setState(resultSet.getString("state"));
                    books.add(book);
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return books;
    }

    public Book findBookById(int id){
        Book book = new Book();
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if (conn != null){
                String query = "SELECT * FROM books WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    book.setName(resultSet.getString("name"));
                    book.setDescription(resultSet.getString("description"));
                    book.setNumberOfPages(resultSet.getInt("number_of_pages"));
                    book.setState(resultSet.getString("sate"));
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return book;
    }

    public void update(int id,Book book){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if(conn != null){
                String query = "UPDATE books SET name = ?,description = ?,number_of_pages = ?,state = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,book.getName());
                preparedStatement.setString(2,book.getDescription());
                preparedStatement.setInt(3,book.getNumberOfPages());
                preparedStatement.setString(4,book.getState());
                preparedStatement.execute();
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
    }

    public void delete(int id){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if(conn != null){
                String query = "DELETE FROM authors WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.execute();
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
    }
}
