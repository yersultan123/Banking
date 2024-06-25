package org.example;

import java.sql.*;
import java.util.Scanner;

import static org.example.User.connection;

public class Transfer {
    String name,surname;
    String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    double money;
    static Scanner sc = new Scanner(System.in);

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = Math.abs(money);
    }

    User user = new User();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    void tran1(User user) throws SQLException {
        System.out.print("to whom? enter number or cancel: +7");
        String first = "+7";
        String second = sc.next();
        String gen = first + second;
        if(!second.equalsIgnoreCase("cancel")) {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/oop", "postgres", "0000");
            PreparedStatement statement = connection.prepareStatement("UPDATE userAccount1 set money=money + ? where phoneNumber=?");
            PreparedStatement statement1 = connection.prepareStatement("UPDATE userAccount1 set money=money - ? where password=? and phoneNumber=?");
            PreparedStatement statement2 = connection.prepareStatement("select name, surname from userAccount1 where phoneNumber=?");
            PreparedStatement statement3 = connection.prepareStatement("select money from userAccount1 where password=? and phoneNumber=?");
            statement2.setString(1, gen);
            ResultSet resultSet = statement2.executeQuery();
            boolean check = false;
            while (resultSet.next()) {
                this.setName(resultSet.getString(1));
                this.setSurname(resultSet.getString(2));
                check = true;
            }
            if (check) {
                System.out.println(this.getName() + " " + this.getSurname() + "?");
                System.out.print("yes or no or cancel:");
                String answer = sc.next();
                if (answer.equalsIgnoreCase("yes")) {
                    System.out.print("How much? ");
                    while (true) {
                        this.setMoney(sc.nextDouble());
                        if (money > 99999999 || money > user.getMoney()) {
                            System.out.println("try again");
                        } else
                            break;
                    }
                    statement1.setDouble(1, this.getMoney());
                    statement1.setString(2, user.getPassword());
                    statement1.setString(3, user.getPhoneNumber());
                    statement1.executeUpdate();
                    statement.setDouble(1, this.getMoney());
                    statement.setString(2, gen);
                    statement.executeUpdate();
                    statement3.setString(1, user.getPassword());
                    statement3.setString(2, user.getPhoneNumber());
                    ResultSet resultSet1 = statement3.executeQuery();
                    while (resultSet1.next()) {
                        user.setMoney(resultSet1.getDouble(1));
                    }
                    PreparedStatement statement6= connection.prepareStatement("select str from tbl3 where one=?");
                    statement6.setInt(1,(int)user.getOne());
                    ResultSet resultSet2=statement6.executeQuery();
                    while (resultSet2.next()){
                        this.setStr(resultSet2.getString(1));
                    }
                    PreparedStatement statement7= connection.prepareStatement("update userAccount1 set money=?,currency=concat(money,?) where phoneNumber=?");
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
                    System.out.println("Balance: " + user.getCurrency());
                } else if (answer.equalsIgnoreCase("cancel")) {
                    System.out.println("ok");
                } else {
                    this.tran1(user);
                }
            } else {
                System.out.println("user not found, try again ");
                this.tran1(user);
            }
        }
        else {
            System.out.println("ok");
        }
    }
    void transfer(User user) throws SQLException {
        String q=this.valid(user);
        if(q.equalsIgnoreCase("false")) {
            System.out.println("password is incorrect, try again");
            this.transfer(user);
        }
        else if(q.equalsIgnoreCase("cancel")){
            System.out.println("ok");
        }
        else {
            PreparedStatement statement6= connection.prepareStatement("select str from tbl3 where one=?");
            statement6.setInt(1,(int)user.getOne());
            ResultSet resultSet2=statement6.executeQuery();
            while (resultSet2.next()){
                this.setStr(resultSet2.getString(1));
            }
            PreparedStatement statement7= connection.prepareStatement("update userAccount1 set currency=concat(money,?) where phoneNumber=?");
            statement7.setString(1,this.getStr());
            statement7.setString(2,user.getPhoneNumber());
            statement7.executeUpdate();
            PreparedStatement statement8= connection.prepareStatement("select currency from userAccount1 where phoneNumber=? and password=?");
            statement8.setString(1, user.getPhoneNumber());
            statement8.setString(2, user.getPassword());
            ResultSet resultSet3 = statement8.executeQuery();
            while (resultSet3.next()){
                user.setCurrency(resultSet3.getString(1));
            }
            System.out.println("Balance: " + user.getCurrency());
            this.tran1(user);
        }
    }
    String valid(User user){
        System.out.print("enter password or cancel: ");
        String password=sc.next();
        if(password.equals(user.getPassword())){
            return "true";
        }
        else if(password.equalsIgnoreCase("cancel")){
            return "cancel";
        }
        else{
            return "false";}
    }
}
