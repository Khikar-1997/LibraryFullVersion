package service;

import customer.Customer;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class CustomerService {
    public static final CustomerService instance = new CustomerService();

    private CustomerService(){
    }

    public void create(Customer customer){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if(conn != null){
                String query = "INSERT INTO Customers(name,surname,password) VALUES(?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,customer.getName());
                preparedStatement.setString(2,customer.getSurname());
                preparedStatement.setString(3,customer.getBCryptPassword());
                preparedStatement.execute();
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
    }

    public ArrayList<Customer> findAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if(conn != null){
                String query = "SELECT * FROM Customers";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    customer.setName(resultSet.getString("name"));
                    customer.setSurname(resultSet.getString("surname"));
                    customer.setPassword(resultSet.getString("password"));
                    customers.add(customer);
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return customers;
    }

    public Customer findCustomerById(int id){
        Customer customer = new Customer();
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if(conn != null){
                String query = "SELECT * FROM Customers WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    customer.setName(resultSet.getString("name"));
                    customer.setSurname(resultSet.getString("surname"));
                    customer.setPassword(resultSet.getString("password"));
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return customer;
    }

    public void update(int id,Customer customer){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if(conn != null){
                String query = "UPDATE Customers SET name = ?,surname = ?,password = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,customer.getName());
                preparedStatement.setString(2,customer.getSurname());
                preparedStatement.setString(3,customer.getBCryptPassword());
                preparedStatement.execute();
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
    }

    public void delete(int id){
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.USER)) {
            if(conn != null){
                String query = "DELETE FROM Customers WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.execute();
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
    }

    public boolean login(int customerId,String password){
        Customer customer = new Customer();
        try(Connection conn = DriverManager.getConnection(MariaDbConstant.DB_URL,MariaDbConstant.USER,MariaDbConstant.PASS)){
            if (conn != null){
                String query = "SELECT password FROM Customers WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1,customerId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    customer.setPassword(resultSet.getString("password"));
                }
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return BCrypt.checkpw(password,customer.getPassword());
    }
}
