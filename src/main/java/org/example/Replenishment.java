package org.example;

import java.sql.*;
import java.util.Scanner;

public class Replenishment {
    double money;
String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = Math.abs(money);
    }
    void replenishment(User user) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter password or cancel");
        String password=sc.nextLine();
        if(password.equals(user.getPassword())){
        System.out.print("how much?");
            while(true){
                this.setMoney(sc.nextDouble());
                if(money > 99999999){
                    System.err.println("try again");
                }
                else
                    break;
            }
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/oop","postgres","0000");
        PreparedStatement statement = connection.prepareStatement("UPDATE userAccount1 set money=money + ? where password=? and phoneNumber=?");
        statement.setDouble(1,this.getMoney());
        statement.setString(2,password);
        statement.setString(3, user.getPhoneNumber());
        statement.executeUpdate();
        PreparedStatement statement1 = connection.prepareStatement("select money from userAccount1 where phoneNumber=?");
        statement1.setString(1, user.getPhoneNumber());
        ResultSet resultSet = statement1.executeQuery();
        while (resultSet.next()){
            user.setMoney(resultSet.getDouble(1));
        }
            PreparedStatement statement6= connection.prepareStatement("select str from tbl3 where one=?");
            statement6.setInt(1,(int)user.getOne());
            ResultSet resultSet2=statement6.executeQuery();
            while (resultSet2.next()){
                this.setStr(resultSet2.getString(1));
            }
            PreparedStatement statement7= connection.prepareStatement("update userAccount1 set currency=concat(?,?) where phoneNumber=?");
            statement7.setDouble(1,user.getMoney());
            statement7.setString(2,this.getStr());
            statement7.setString(3,user.getPhoneNumber());
            statement7.executeUpdate();
            PreparedStatement statement8= connection.prepareStatement("select currency from userAccount1 where phoneNumber=? and password=?");
            statement8.setString(1, user.getPhoneNumber());
            statement8.setString(2, user.getPassword());
            ResultSet resultSet3 = statement8.executeQuery();
            while (resultSet3.next()){
                user.setCurrency(resultSet3.getString(1));
            }
        System.out.println("Balance replenished by " + this.getMoney() + this.getStr());
        System.out.println("Balance: " + user.getCurrency());
        }
        else if(password.equalsIgnoreCase("cancel")){
            System.out.println("ok");
        }
        else{
            System.err.println("password is wrong, try again");
            this.replenishment(user);
        }
    }
}
