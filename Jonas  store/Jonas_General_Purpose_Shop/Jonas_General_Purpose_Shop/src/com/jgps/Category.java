package com.jgps;

import java.sql.*;
import java.util.Scanner;

public interface Category {
    DbLogin d = new DbLogin();
void groceryproducts ();
void sportsItems ();


    void ShowData(String tnm);

}

abstract class groceryproducts implements Category{
    public void groceryproducts(){
        try{
            Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);
            Statement stm = conn.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS Grocery_Items (product_id int(10) PRIMARY KEY AUTO_INCREMENT, product_name varchar(50), category varchar(100), product_price double(10,2))";
            stm.execute(query);
            String query2 = "INSERT INTO Grocery_Items (product_name, category, product_price) VALUES (?,?,?)";

            PreparedStatement pstm = conn.prepareStatement(query2);
            pstm.setString(1, "Soybean Oil");
            pstm.setString(2, "grocery");
            pstm.setDouble(3, 250);
            pstm.execute();
            pstm.setString(1, "Fish");
            pstm.setString(2, "grocery");
            pstm.setDouble(3, 500);
            pstm.execute();
            pstm.setString(1, "Meat");
            pstm.setString(2, "grocery");
            pstm.setDouble(3, 900);
            pstm.execute();
            pstm.setString(1, "Plain cake");
            pstm.setString(2, "grocery");
            pstm.setDouble(3, 100);
            pstm.execute();
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
abstract class sportsItems implements Category{
    public void sportsItems(){
        try{
            Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);
            Statement stm = conn.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS sportsItems (product_id int(10) PRIMARY KEY AUTO_INCREMENT, product_name varchar(50), category varchar(100), product_price double(10,2))";
            stm.execute(query);
            String query2 = "INSERT INTO sportsItems (product_name, category, product_price) VALUES (?,?,?)";

            PreparedStatement pstm = conn.prepareStatement(query2);
            pstm.setString(1, "Bat");
            pstm.setString(2, "sportsItems");
            pstm.setDouble(3, 5000);
            pstm.execute();
            pstm.setString(1, "Ball");
            pstm.setString(2, "sportsItems");
            pstm.setDouble(3, 500);
            pstm.execute();
            pstm.setString(1, "Pad");
            pstm.setString(2, "sportsItems");
            pstm.setDouble(3, 1200);
            pstm.execute();
            pstm.setString(1, "Jersey");
            pstm.setString(2, "sportsItems");
            pstm.setDouble(3, 800);
            pstm.execute();

            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
abstract class ShowData implements Category {
    public void ShowData(String tnm) {
        try {
            Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);

            String query = String.format("select * from %s", tnm);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            System.out.println("================================");
            System.out.println(String.format("%s",tnm));
            System.out.println("================================");
            System.out.println("Product ID        Product name         Product price");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t\t\t\t\t\t " + rs.getString(2) + "\t\t\t\t\t\t" + rs.getDouble(4));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
