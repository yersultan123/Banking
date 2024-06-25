package org.example;

import java.sql.*;
import java.util.Scanner;

public class HomePage {
    static Scanner sc = new Scanner(System.in);
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/oop","postgres","0000");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean k=false;
    void profileGeneral(User user) throws SQLException {
        System.out.println("                            Profile                               ");
        System.out.println("1.User info | 2.Log out | 3.Change Password | 4.Delete userAccount");
        this.choice(user);
    }
    void choice(User user) throws SQLException {
        int choice2 = sc.nextInt();
        switch (choice2){
            case 1:
                this.profile(user);
                break;
            case 2:
                this.logout();
                break;
            case 3:
                this.changePassword(user);
                break;
            case 4:
                this.deleteAccount(user);
                break;
            default:
                System.out.println("wrong,try again");
                this.choice(user);
        }
    }
    void profile(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select name,surname,money,user_date,currency,one from userAccount1 where phoneNumber=? and password=?");
        statement.setString(1,user.getPhoneNumber());
        statement.setString(2,user.getPassword());
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            user.setName(resultSet.getString(1));
            user.setSurname(resultSet.getString(2));
            user.setMoney(resultSet.getDouble(3));
            user.setDate(resultSet.getString(4));
            user.setCurrency(resultSet.getString(5));
            user.setOne(resultSet.getInt(6));
        }
        System.out.println("Name: "+user.getName()+" " + "Surname: "+user.getSurname());
        System.out.println("Balance:" +user.getCurrency() + " date of registration: " + user.getDate());
    }
    void logout(){
        k=true;
    }
    void changePassword(User user) throws SQLException {
        System.out.print("enter current password:");
        user.setPassword(sc.next());
        System.out.print("enter phoneNumber(format +7*******): +7");
        String phoneNumber="+7"+sc.next();
        System.out.print("new password:");
        String password=sc.next();
        PreparedStatement statement = connection.prepareStatement("update userAccount1 set password=? where password=? and phoneNumber=?");
        statement.setString(1,password);
        statement.setString(2,user.getPassword());
        statement.setString(3,phoneNumber);
        statement.executeUpdate();
        PreparedStatement statement1 = connection.prepareStatement("select password from userAccount1");
        ResultSet resultSet = statement1.executeQuery();
        int check = 0;
        while(resultSet.next()){
            user.setPassword(resultSet.getString(1));
            check++;
        }
        if (check>0){
            System.out.println("password changed successfully to "+user.getPassword());
        }
        else {
            System.out.println("current password is wring try again");
            this.changePassword(user);
        }
    }
    void deleteAccount(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from userAccount1 where password=? and phoneNumber=?");
        statement.setString(1,user.getPassword());
        statement.setString(2, user.getPhoneNumber());
        statement.executeUpdate();
        System.out.println("successfully deleted!");
        k=true;
    }
}
