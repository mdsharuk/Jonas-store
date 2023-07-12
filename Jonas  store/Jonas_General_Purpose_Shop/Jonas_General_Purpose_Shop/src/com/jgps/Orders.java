package com.jgps;

import java.sql.*;
import java.util.Scanner;

class Orders{
    DbLogin d = new DbLogin();
    public void selectProducts(int pid, String tnm){
        try {
            Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);
            Statement stm = conn.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS orders (p_id int(10) PRIMARY KEY AUTO_INCREMENT, product_name varchar(50), category varchar(100), product_price double(10,2))";
            stm.execute(query);
            String query2 = String.format("INSERT INTO orders (product_name, category, product_price) SELECT product_name, category, product_price FROM %s WHERE product_id = %d",tnm,pid);
            stm.execute(query2);
            System.out.println("Product Selected Successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String totalPrice(){
        String sum = "";
        try {
            Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);
            Statement stm = conn.createStatement();

            String query2 = "SELECT SUM(product_price) FROM orders";
            ResultSet res = stm.executeQuery(query2);
            res.next();
            sum += res.getString(1);
            System.out.println("------------------------------------------------------------");
            System.out.println("Total Price:\t\t\t\t\t\t\t\t\t\t" + sum);


        }catch (SQLException e){
            e.printStackTrace();
        }
        return sum;
    }
    public void ShowOrders() {
        try {
            Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);

            String query = "select * from orders";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            System.out.println("===================================");
            System.out.println("\t\t\t\t\t\t\t\t Orders");
            System.out.println("===================================");
            System.out.println("Product ID        Product name         Product price");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t\t\t\t\t\t " + rs.getString(2) + "\t\t\t\t\t\t" + rs.getDouble(4));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean payment(){
        boolean isTrue = true;
                try{
                    Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);
                    Statement stm = conn.createStatement();

                    String query = "CREATE TABLE IF NOT EXISTS payment(payment_Id int(10) PRIMARY KEY AUTO_INCREMENT, Transaction_number varchar(20), Payment_method varchar(20), Payment_Status varchar(25))";
                    stm.execute(query);

                    Scanner sc = new Scanner(System.in);
                    System.out.println("Your order is confirmed.");

                    String transNumber, payStatus;
                    System.out.print("Enter transaction ID: ");
                    transNumber = sc.next();

                     if (transNumber!=null){
                         System.out.println("Your payment is confirmed.");
                         payStatus = "Paid";
                     }
                     else {
                         System.out.println("There was an error in your transaction. Try again!!!");
                         payStatus = "Not paid";
                         isTrue=false;
                     }

                     String query2 = "INSERT INTO payment(Transaction_number, Payment_method, Payment_Status) VALUES (?,?,?)";
                     PreparedStatement pstm = conn.prepareStatement(query2);
                     pstm.setString(1, transNumber);
                     pstm.setString(2, "bKash");
                     pstm.setString(3, payStatus);
                     pstm.execute();

                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
                return isTrue;
    }
    
    public void clearOrder(){
        try{
            Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);
            Statement stm = conn.createStatement();

            String query = "DROP TABLE orders";
            stm.execute(query);
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}