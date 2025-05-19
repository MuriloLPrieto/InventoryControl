package app;

import model.Clients;
import model.Products;
import services.ClientServices;
import services.ProductServices;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ClientServices clientServices = new ClientServices();
            ProductServices productServices = new ProductServices();

            while (true) {
                System.out.println("Hi, welcome to CRUD");
                System.out.println("------------------------------------------------------------------------------");

                System.out.println("""
                                Enter the option that you want:
                                1- Clients
                                2- Products
                                0-Exit""");
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 0:
                        System.out.println("Bye, see you next time :)");
                        break;
                    case 1:
                        MenuHandler.clientMenu(clientServices, scanner);
                        break;
                    case 2:
                        MenuHandler.productMenu(productServices, scanner);
                    default:
                        System.out.println("Error! Please enter a number between 0 and 2.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Number format is invalid!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error! An illegal argument found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error! An Exception has been occurred: " + e.getMessage());
        }
    }
}