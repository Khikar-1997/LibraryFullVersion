package controller;

import customer.Customer;
import service.CustomerService;

import java.util.Scanner;

public class CustomerController {
    private static final CustomerController instance = new CustomerController();
    private CustomerService customerService = CustomerService.instance;
    private Scanner scanner = new Scanner(System.in);
    private CustomerController(){
    }

    private void createCustomer() {
        System.out.println("Please write customer name");
        String name = scanner.nextLine();
        System.out.println("Please write customer surname");
        String surname = scanner.nextLine();
        System.out.println("Please write customer password");
        String password = scanner.nextLine();
        customerService.create(new Customer(name,surname,password));
    }

    private void selectAllCustomers() {
        customerService.findAllCustomers();
    }

    private void selectCustomer(){
        System.out.println("Please write customer id");
        int id = scanner.nextInt();
        customerService.findCustomerById(id);
    }

    private void updateCustomer() {
        System.out.println("Please write customer id");
        int id = scanner.nextInt();
        System.out.println("Please write customer new name");
        String name = scanner.nextLine();
        System.out.println("Please write customer new surname");
        String surname = scanner.nextLine();
        System.out.println("Please write customer new password");
        String password = scanner.nextLine();
        customerService.update(id,new Customer(name,surname,password));
    }

    private void deleteCustomer() {
        System.out.println("Please write customer id");
        int id = scanner.nextInt();
        customerService.delete(id);
    }

    private void login() {
        System.out.println("Please write customer id");
        int id = scanner.nextInt();
        String test = scanner.nextLine();
        System.out.println("Plese write customer password");
        String password = scanner.nextLine();
        if (customerService.login(id,password)) {
            System.out.println("You are login");
            System.out.println("________________________________________");
            System.out.println("     Save or not save password          ");
            System.out.println();
            System.out.println("   Press 1 save your password        ");
            System.out.println("   Press 2 unsaved your password     ");
            System.out.println("   Press 3 only this turn            ");
            System.out.println("________________________________________");
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    System.out.println("Your password is saved");
                    break;
                case 2:
                    System.out.println("Yoru password is not saved");
                    break;
                case 3:
                    System.out.println("Your password is saved");
                    break;
                default:
                    System.out.println("Unvalid order!!!!!!!!!!");
                    break;
            }

        } else {
            System.out.println("Unvalid password or username");
        }
    }

    public static void customersMenu() {
        CustomerController customerController = CustomerController.instance;
        int num;
        do {
            System.out.println("___________________________________");
            System.out.println("             Customers             ");
            System.out.println("   Press 1 to create customer      ");
            System.out.println("   Press 2 to select all customers ");
            System.out.println("   Press 3 to select customer      ");
            System.out.println("   Press 4 to update customer      ");
            System.out.println("   Press 5 to delete customer      ");
            System.out.println("   Press 6 to login customer       ");
            System.out.println("   Press 7 to return menu          ");
            System.out.println("___________________________________");

            Scanner scanner = new Scanner(System.in);
            num = scanner.nextInt();
            String test = scanner.nextLine();
            switch (num) {
                case 1:
                    customerController.createCustomer();
                    break;
                case 2:
                    customerController.selectAllCustomers();
                    break;
                case 3:
                    customerController.selectCustomer();
                    break;
                case 4:
                    customerController.updateCustomer();
                    break;
                case 5:
                    customerController.deleteCustomer();
                    break;
                case 6:
                    customerController.login();
                    break;
                case 7:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Plese write valid order");
            }
        } while (num != 7);
    }
}
