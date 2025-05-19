package app;

import model.Clients;
import model.Products;
import services.ClientServices;
import services.ProductServices;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MenuHandler {

    public static void clientMenu(ClientServices clientServices, Scanner scanner) {
        try {
            Clients newClient = null;
            while (true) {
                System.out.println("""
                        === CLIENTS ===
                        1 - Register new client
                        2 - List clients
                        3 - Search clients by ID
                        4 - Update name/email
                        5 - Remove client (soft delete)
                        0 - Exit""");

                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 0:
                        System.out.println("Returning to main menu");
                        return;
                    case 1:
                        System.out.print("Enter the name: ");
                        String clientName = scanner.nextLine();
                        System.out.print("Enter the email: ");
                        String clientEmail = scanner.nextLine();
                        newClient = clientServices.createClient(new Clients(clientName, clientEmail));
                        System.out.println("Clients has been created with id: " + newClient.getId());
                        break;
                    case 2:
                        List<Clients> clientsList = clientServices.listAllClients();
                        clientsList.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.print("Enter the id: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Clients findClient = clientServices.listById(id);
                        System.out.println(findClient);
                        break;
                    case 4:

                        System.out.println("Enter the client id that you want to update: ");
                        int idToUpdate = Integer.parseInt(scanner.nextLine());

                        Clients clientsToUpdate = clientServices.listById(idToUpdate);

                        if (clientsToUpdate == null) {
                            System.out.println("Error! No client in memory, please create one first!");
                            return;
                        }

                        System.out.println("""
                                Enter the option that you want:
                                1- Update name
                                2- Update email
                                0-Exit
                                """);
                        option = Integer.parseInt(scanner.nextLine());
                        switch (option) {
                            case 0:
                                System.out.println("Returning to main menu");
                                return;
                            case 1:
                                System.out.print("Enter the new name: ");
                                String newName = scanner.nextLine();

                                clientsToUpdate.setName(newName);
                                clientServices.updateName(clientsToUpdate);
                                break;
                            case 2:
                                System.out.print("Enter the new email: ");
                                String newEmail = scanner.nextLine();

                                clientsToUpdate.setEmail(newEmail);
                                clientServices.updateEmail(clientsToUpdate);
                                break;
                            default:
                                System.out.println("Error! Please enter a number between 0 and 2.");
                        }
                        break;
                    case 5:
                        System.out.println("Enter the client id: ");
                        int idRemove = Integer.parseInt(scanner.nextLine());

                        System.out.println("Do you really want exclude the client: " + clientServices.listById(idRemove) + "? Type 'Y' to exclude or 'N' to cancel");
                        String verification = scanner.nextLine();
                        if (verification.equalsIgnoreCase("Y")) {
                            clientServices.softDelete(idRemove);
                            System.out.println("Client has been removed");
                        } else {
                            System.out.println("Operation canceled");
                        }
                        break;
                    default:
                        System.out.println("Error! Please enter a number between 0 and 5.");
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void productMenu(ProductServices productServices, Scanner scanner) {
        try {
            Products newProduct = null;
            while (true) {
                System.out.println("""
                        === PRODUCTS ===
                        1 - Register new product
                        2 - List products
                        3 - Search products by ID
                        4 - Update products
                        5 - Remove product (soft delete)
                        0 - Exit""");

                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 0:
                        System.out.println("Returning to main menu");
                        return;
                    case 1:
                        System.out.print("Enter the product name: ");
                        String productName = scanner.nextLine();
                        System.out.print("Enter the product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        System.out.print("Enter the quantity in stock: ");
                        int stock = Integer.parseInt(scanner.nextLine());

                        newProduct = productServices.CreateProducts(new Products(productName, price, stock));
                        System.out.println("Product has been created with id: " + newProduct.getId());
                        break;
                    case 2:
                        List<Products> productsList = productServices.listAll();
                        productsList.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.print("Enter the product id: ");
                        int productId = Integer.parseInt(scanner.nextLine());
                        Products productFounded = productServices.listById(productId);
                        System.out.println(productFounded);
                        break;
                    case 4:
                        System.out.println("Enter the product id to update: ");
                        int idToUpdateProduct = Integer.parseInt(scanner.nextLine());
                        Products productToUpdate = productServices.listById(idToUpdateProduct);

                        if (productToUpdate == null) {
                            System.out.println("Error! No product in memory, please create one first!");
                            return;
                        }

                        System.out.println("""
                                Enter the option that you want:
                                1-Update name
                                2- Update price
                                3- Update Stock
                                4- Update all information's
                                0-Exit
                                """);

                        option = Integer.parseInt(scanner.nextLine());
                        switch (option) {
                            case 0:
                                System.out.println("Returning to main menu");
                                return;
                            case 1:
                                System.out.println("Enter the new name: ");
                                String newProductName = scanner.nextLine();
                                productToUpdate.setName(newProductName);
                                productServices.updateName(productToUpdate);
                                break;
                            case 2:
                                System.out.println("Enter the new price: ");
                                BigDecimal newPrice = new BigDecimal(scanner.nextLine());
                                productToUpdate.setPrice(newPrice);
                                productServices.updatePrice(productToUpdate);

                                break;
                            case 3:
                                System.out.println("Enter the new stock");
                                int newStock = Integer.parseInt(scanner.nextLine());
                                productToUpdate.setStock(newStock);
                                productServices.updateStock(productToUpdate);
                                break;
                            case 4:
                                System.out.println("Enter the new name");
                                newProductName = scanner.nextLine();
                                System.out.println("Enter the new price: ");
                                newPrice = new BigDecimal(scanner.nextLine());
                                System.out.println("Enter the new stock");
                                newStock = Integer.parseInt(scanner.nextLine());

                                productToUpdate.setName(newProductName);
                                productToUpdate.setPrice(newPrice);
                                productToUpdate.setStock(newStock);
                                productServices.updateProduct(productToUpdate);
                                break;
                            default:
                        }

                        break;
                    case 5:
                        System.out.print("Enter the product id that you want to remove: ");
                        int productIdToRemove = Integer.parseInt(scanner.nextLine());

                        System.out.println("Do you really want to exclude the product " + productServices.listById(productIdToRemove) + "? Type 'Y' to exclude or 'N' to cancel");
                        String productVerification = scanner.nextLine();
                        if (productVerification.equalsIgnoreCase("Y")) {
                            productServices.softDelete(productIdToRemove);
                            System.out.println("The product has been excluded");
                        } else {
                            System.out.println("Operation has been canceled");
                        }
                        break;
                    default:
                        System.out.println("Error! Please enter a number between 0 and 5.");
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}