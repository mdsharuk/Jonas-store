package com.jgps;
import java.sql.*;
import java.util.Scanner;

class User {
    DbLogin d = new DbLogin();
    public boolean signup(){
     boolean isTrue = true;
        try{
            Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);
            Statement stm = conn.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS users (user_id int(10) PRIMARY KEY NOT NULL AUTO_INCREMENT, user_name varchar(50) NOT NULL, user_email varchar(100) NOT  NULL, user_pw varchar(100) NOT NULL)";
            stm.execute(query);

            String query2 = "INSERT INTO users (user_name, user_email, user_pw) VALUES (?,?,?)";
            Scanner sc = new Scanner(System.in);
            PreparedStatement pstm = conn.prepareStatement(query2);
            String name;
            String email;
            String pass;

            System.out.print("Name = ");
            name = sc.next();
            System.out.print("Email = ");
            email = sc.next();
            System.out.print("pw = ");
            pass = sc.next();

            pstm.setString(1, name);
            pstm.setString(2, email);
            pstm.setString(3, pass);
            pstm.execute();
            System.out.println("Account created successfully");
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return isTrue;
    }
    public boolean login(String usrname, String userpw){
        boolean isTrue = true;
            try {
                Connection conn = DriverManager.getConnection(d.url, d.usrname, d.pw);
                if (usrname != null && userpw != null) {
                    String query = "Select * from users Where user_name='" + usrname + "' and user_pw='" + userpw + "'";
                    Statement stm = conn.createStatement();
                    ResultSet rs = stm.executeQuery(query);
                    if (rs.next()) {
                        //in this case enter when at least one result comes it means user is valid
                        System.out.println("Logged in successfully");
                    }
                    else {
                        //in this case enter when  result size is zero  it means user is invalid
                        System.out.println("usrname or pw is invalid.");
                    isTrue = false;
                    }
                }
            } catch (SQLException e) {
                System.out.println("You didn't signup. Please signup first.");
                isTrue = false;
            }
        return isTrue;
    }
}




