package com.jgps;

import java.util.Scanner;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        DbLogin d = new DbLogin();
        Orders orders = new Orders();
        User user = new User();
        groceryproducts grocery = new groceryproducts() {

            public void groceryproducts() {
                super.groceryproducts();
            }

            @Override
            public void sportsItems() {

            }


            @Override
            public void ShowData(String tnm) {

            }


        };

        sportsItems cos = new sportsItems() {
            @Override
            public void sportsItems(){
                super.sportsItems();
            }
            @Override
            public void groceryproducts() {

            }

            @Override
            public void ShowData(String tnm) {

            }


        };
        ShowData show = new ShowData() {

            public void ShowData(String tnm) {
                super.ShowData(tnm);
            }

            @Override
            public void groceryproducts() {

            }

            @Override
            public void sportsItems() {

            }


        };

        Scanner scanner = new Scanner(System.in);
        //DATABASE Connectivity
        try {
            String url = "jdbc:mysql://localhost:3306/";
            Connection conn = DriverManager.getConnection(url, d.usrname, d.pw);
            Statement stm = conn.createStatement();

            String query = "CREATE DATABASE IF NOT EXISTS jgps";
            stm.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Interface
        System.out.println("Welcome to JGPS (Jonas General Purpose Shop)");
        System.out.println("1. login");
        System.out.println("2. Sign Up");

        int choice = scanner.nextInt();

        String usrname, pw;

        if (choice == 1) {
            System.out.println("Enter your usrname: ");
            usrname = scanner.next();
            System.out.println("Enter your pw: ");
            pw = scanner.next();
            if (user.login(usrname, pw)) {
                int c = 0;
                while (c != 3) {
                    System.out.println("Select anyone from below:");
                    System.out.println("1. Go to categories and select products");
                    System.out.println("2. Order selected products");
                    System.out.println("3. Exit app");
                    System.out.print("Enter your choice: ");
                    c = scanner.nextInt();
                    if (c == 1) {
                        int co = 0;
                        while (co != 3) {
                            System.out.println("Select any category and choose your products:");
                            System.out.println("1. Grocery Items\n2. sportsItems Items\n3. Go Back");
                            System.out.print("Enter your choice: ");
                            co = scanner.nextInt();
                            if (co == 1) {
                                    show.ShowData("Grocery_Items");
                                    System.out.print("Select product by entering id: ");
                                    int pid = scanner.nextInt();
                                    scanner.nextLine();
                                    orders.selectProducts(pid, "Grocery_Items");
                            } else if (co == 2) {
                                    show.ShowData("sportsItems");
                                    System.out.print("Select product by entering id: ");
                                    int pid = scanner.nextInt();
                                    scanner.nextLine();
                                    orders.selectProducts(pid, "sportsItems");
                            }
                        }
                    } else if (c == 2) {
                        orders.ShowOrders();
                        orders.totalPrice();
                        System.out.println("Press 1 to confirm your order");
                        int cho = scanner.nextInt();
                        if (cho == 1) {
                            if (orders.payment()) ;
                            orders.clearOrder();
                        }
                        }
                    }
                }
        } else if (choice == 2) {
            if(user.signup()){
                grocery.groceryproducts();
                cos.sportsItems();
            }
        }
    }
}

