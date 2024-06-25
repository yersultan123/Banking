package org.example;

import java.sql.*;
import java.util.Scanner;

public class User {
    private String name,phoneNumber,surname,password,date,currency;
    double money,one;

    public double getOne() {
        return one;
    }

    public void setOne(double one) {
        this.one = one;
    }

    static Scanner sc = new Scanner(System.in);
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/oop","postgres","0000");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User(String name, String phoneNumber, String surname, String password, double money) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.surname = surname;
        this.password = password;
        this.money = money;
    }
    public User(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        try{
        this.money = Math.abs(money);}
        catch (Exception e){
            System.out.println("wrong");
        }
    }
    public void createUser() throws SQLException {
        System.out.print("enter name:");
        setName(sc.next());
        sc.nextLine();
        System.out.print("enter surname:");
        setSurname(sc.next());
        sc.nextLine();
        System.out.print("enter phone number(format +7*******): +7");
        setPhoneNumber("+7"+sc.next());
        System.out.print("enter password:");
        setPassword(sc.next());
        sc.nextLine();
        PreparedStatement statement = connection.prepareStatement("insert into userAccount1(name,surname,phoneNumber,password,money,currency,one) values (?,?,?,?,0,'0tg',?);");
        statement.setString(1,getName());
        statement.setString(2,getSurname());
        statement.setString(3,getPhoneNumber());
        statement.setString(4,getPassword());
        statement.setInt(5,9);
        statement.executeUpdate();
        PreparedStatement statement4= connection.prepareStatement("update userAccount1 set currency=concat(money,' tg') where phoneNumber=?");
        statement4.setString(1,getPhoneNumber());
        statement4.executeUpdate();
        PreparedStatement statement1 = connection.prepareStatement("select user_date,one from userAccount1 where phoneNumber=? and password=?");
        statement1.setString(1,getPhoneNumber());
        statement1.setString(2,getPassword());
        ResultSet resultSet = statement1.executeQuery();
        while(resultSet.next()){
            this.setDate(resultSet.getString(1));
            this.setOne(resultSet.getInt(2));
        }
        PreparedStatement statement5= connection.prepareStatement("select currency from userAccount1 where phoneNumber=?");
        statement5.setString(1,this.getPhoneNumber());
        ResultSet resultSet1 = statement5.executeQuery();
        while (resultSet1.next()){
            this.setCurrency(resultSet1.getString(1));
        }
    }
    public void logIn() throws SQLException {
        System.out.print("enter phone number(format +7*******): +7");
        setPhoneNumber("+7"+sc.next());
        System.out.print("enter password: ");
        setPassword(sc.next());
        PreparedStatement statement = connection.prepareStatement("select name,surname,money,user_date,currency,one from userAccount1 where phoneNumber=? and password=?");
        statement.setString(1,getPhoneNumber());
        statement.setString(2,getPassword());
        ResultSet resultSet = statement.executeQuery();
        int check=0;
        while(resultSet.next()){
            this.setName(resultSet.getString(1));
            this.setSurname(resultSet.getString(2));
            this.setMoney(resultSet.getDouble(3));
            this.setDate(resultSet.getString(4));
            this.setCurrency(resultSet.getString(5));
            this.setOne(resultSet.getInt(6));
            check++;
        }
        if(check>0){
            System.out.println("welcome " + getName());
        }
        else{
            System.out.println("user not found): wanna create a new user? or stay? select 1 or 2");
            if(sc.nextInt()==1){
                this.createUser();
            }
            else{
            this.logIn();}
        }
    }
}
