package com.pluralsight;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class OnlineStore {
    //menu in main
    public static HashMap<String, Product> cart = new HashMap<String, Product>();
    static float total = 0;
    public static void main(String[] args) {
        displayMenu();


    }//end main

    //method that displays menu
    public static void displayMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 1 - 3");
        System.out.println("(1) Display Products");
        System.out.println("(2) Display Cart");
        System.out.println("(3) Exit");
        choice = scanner.nextInt();
        //menu decision structure
        if (choice == 1) {
            displayProducts();
        } else if (choice == 2) {
            displayCart();
        } else if (choice == 3) {
            System.exit(0);
        }
    }

    public static HashMap displayProducts() {//displays list of products that your store sells -search - add to cart - go back

        Scanner scanner = new Scanner(System.in);
        int choice;


        //create hashmap for list of all products
        HashMap<String, Product> allProducts = new HashMap<String, Product>();
        //read text file
        try {
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //iterate through text file and add to Product object
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] line = input.split("\\|");
                //ignore first line
                if (!line[0].contains("SKU")) {


                    String id = line[0];
                    String name = line[1];
                    float price = Float.parseFloat(line[2]);
                    String department = line[3];
                    //add each thing into hashmap of inventory
                    allProducts.put(id, new Product(id, name, price, department));
                }

            }
            //display all products
            for (Map.Entry<String, Product> entry : allProducts.entrySet()) {
                String key = entry.getKey();
                Product value = entry.getValue();
                System.out.println(value.getId() + " |" + value.getName()
                        + " |" + value.getPrice() + " |" + value.getDepartment());

            }
            //prompt user input
            System.out.println("(1)Add a product to cart");
            System.out.println("(2)Go Back");
            choice = scanner.nextInt();
            //if user choses add then have user enter ID of item
            if (choice == 1) {
                //create cart hashmap


                System.out.println("Please select an item to add to your cart by ID: ");
                scanner.nextLine();
                String userSelect = scanner.nextLine().trim();

                if (allProducts.containsKey(userSelect)) {
                    // difining new hashmap using user input for id, and ref to original hash
                    cart.put(userSelect, new Product(userSelect, allProducts.get(userSelect).getName(), allProducts.get(userSelect).getPrice(), allProducts.get(userSelect).getDepartment()));
                    total = total + cart.get(userSelect).getPrice();// adding price of each item
                    //output
                    for (Map.Entry<String, Product> entry : cart.entrySet()) {
                        String key = entry.getKey();
                        Product value = entry.getValue();
                        System.out.println(value.getId() + " |" + value.getName()
                                + " |" + value.getPrice() + " |" + value.getDepartment());

                    }
                    System.out.println("Has been added to your cart.");
                    displayMenu();
                }
            } else if (choice == 2) {
                //put display menu here
                displayMenu();
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
        return cart;
    }//end display products

    public static void displayCart() {
        System.out.println("Your Cart: ");
        for (Map.Entry<String, Product> entry : cart.entrySet()) {
            String key = entry.getKey();
            Product value = entry.getValue();
            System.out.println(value.getId() + " |" + value.getName()
                    + " |" + value.getPrice() + " |" + value.getDepartment());

        }//end for
        // check out, remove product from cart, go back to home screen
        int choice;
        Scanner keyboard = new Scanner(System.in);
        String userChoice;
        //displaycart menu options
        System.out.println("(1) Check out");
        System.out.println("(2) Remove Product");
        System.out.println("(3) Go Back");
        choice = keyboard.nextInt();
        keyboard.nextLine();
        //method decision structure
        if (choice == 3) {
            displayMenu();
        } else if (choice == 2) {
            System.out.println("Please select the Item you wish to remove by ID: ");
            userChoice = keyboard.nextLine();
            cart.remove(userChoice);
            total = total - cart.get(userChoice).getPrice();
            displayMenu();


        } else if (choice == 1) {
            System.out.println("Your Cart: ");
            for (Map.Entry<String, Product> entry : cart.entrySet()) {
                String key = entry.getKey();
                Product value = entry.getValue();
                System.out.println(value.getId() + " |" + value.getName()
                        + " |" + value.getPrice() + " |" + value.getDepartment());

                System.out.println("Total Sales: $" + total);
                displayMenu();

            }


        }


    }//end displayCart
}//end class