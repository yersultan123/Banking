package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static org.example.User.connection;

public class Currency {
    double one,usd,eur,gbp,jpy,chp,cad,aud,cny,res;
    static Scanner sc = new Scanner(System.in);
String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public double getOne() {
        return one;
    }

    public void setOne(double one) {
        this.one = one;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public double getGbp() {
        return gbp;
    }

    public void setGbp(double gbp) {
        this.gbp = gbp;
    }

    public double getJpy() {
        return jpy;
    }

    public void setJpy(double jpy) {
        this.jpy = jpy;
    }

    public double getChp() {
        return chp;
    }

    public void setChp(double chp) {
        this.chp = chp;
    }

    public double getCad() {
        return cad;
    }

    public void setCad(double cad) {
        this.cad = cad;
    }

    public double getAud() {
        return aud;
    }

    public void setAud(double aud) {
        this.aud = aud;
    }

    public double getCny() {
        return cny;
    }

    public void setCny(double cny) {
        this.cny = cny;
    }

    @Override
    public String toString() {
        return "one=" + one +
                ", usd=" + usd +
                ", eur=" + eur +
                ", gbp=" + gbp +
                ", jpy=" + jpy +
                ", chp=" + chp +
                ", cad=" + cad +
                ", aud=" + aud +
                ", cny=" + cny;
    }
    void currency(User user) throws SQLException, IOException {
        Document document = Jsoup.connect("https://www.fxexchangerate.com").get();
        Elements cur = document.select("span.close");
        String cu = cur.text();
        Elements table = document.getElementsByTag("table");
        Elements tBody = table.select("tBody");
        Elements tr = tBody.select("tr");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/oop", "postgres", "0000");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO tbl2(one,USD,EUR,GBP,JPY,CHF,CAD,AUD,CNY,tg) VALUES(?,?,?,?,?,?,?,?,?,?);");
        int k = 0, n = 1;
        statement.setDouble(1, 1);
        while (k != 8) {
            k++;
            n++;
            statement.setDouble(n, Double.parseDouble(tr.select("td").get(k).text()));
            statement.setDouble(10, 446.56);
        }
        statement.executeUpdate();
        k = 18;
        n = 1;
        statement.setDouble(1, 2);
        while (k != 26) {
            k++;
            n++;
            statement.setDouble(n, Double.parseDouble(tr.select("td").get(k).text()));
            statement.setDouble(10, 477);
        }
        statement.executeUpdate();
        k = 36;
        n = 1;
        statement.setDouble(1, 3);
        while (k != 44) {
            k++;
            n++;
            statement.setDouble(n, Double.parseDouble(tr.select("td").get(k).text()));
            statement.setDouble(10, 537);
        }
        statement.executeUpdate();
        k = 54;
        n = 1;
        statement.setDouble(1, 4);
        while (k != 62) {
            k++;
            n++;
            statement.setDouble(n, Double.parseDouble(tr.select("td").get(k).text()));
            statement.setDouble(10, 3.33);
        }
        statement.executeUpdate();
        k = 72;
        n = 1;
        statement.setDouble(1, 5);
        while (k != 80) {
            k++;
            n++;
            statement.setDouble(n, Double.parseDouble(tr.select("td").get(k).text()));
            statement.setDouble(10, 483);
        }
        statement.executeUpdate();
        k = 90;
        n = 1;
        statement.setDouble(1, 6);
        while (k != 98) {
            k++;
            n++;
            statement.setDouble(n, Double.parseDouble(tr.select("td").get(k).text()));
            statement.setDouble(10, 331);
        }
        statement.executeUpdate();
        k = 108;
        n = 1;
        statement.setDouble(1, 7);
        while (k != 116) {
            k++;
            n++;
            statement.setDouble(n, Double.parseDouble(tr.select("td").get(k).text()));
            statement.setDouble(10, 308);
        }
        statement.executeUpdate();
        k = 126;
        n = 1;
        statement.setDouble(1, 8);
        while (k != 134) {
            k++;
            n++;
            statement.setDouble(n, Double.parseDouble(tr.select("td").get(k).text()));
            statement.setDouble(10, 65);
        }
        statement.executeUpdate();
        statement.setDouble(1, 9);
        statement.setDouble(2, 0.0022);
        statement.setDouble(3, 0.0021);
        statement.setDouble(4, 0.0019);
        statement.setDouble(5, 0.30);
        statement.setDouble(6, 0.003);
        statement.setDouble(7, 0.0021);
        statement.setDouble(8, 0.0032);
        statement.setDouble(9, 0.015);
        statement.setDouble(10, 1);
        statement.executeUpdate();
        PreparedStatement statement1 = connection.prepareStatement("select * from  tbl2");
        PreparedStatement statement2 = connection.prepareStatement("delete from tbl2");
        ResultSet resultSet = statement1.executeQuery();
        while (resultSet.next()) {
            Currency currency = new Currency();
            currency.setOne(resultSet.getDouble(1));
            currency.setUsd(resultSet.getDouble(2));
            currency.setEur(resultSet.getDouble(3));
            currency.setGbp(resultSet.getDouble(4));
            currency.setJpy(resultSet.getDouble(5));
            currency.setChp(resultSet.getDouble(6));
            currency.setCad(resultSet.getDouble(7));
            currency.setAud(resultSet.getDouble(8));
            currency.setCny(resultSet.getDouble(9));
        }
        System.out.println("select 1 to convert money, 2 to open calculator");
        int w = sc.nextInt();
        if (w == 2) {
            System.out.println("1-usd , 2-eur, 3-gbp, 4-jpy, 5-chp, 6-cad,7-aud,8-cny");
            System.out.print("from: ");
            int y = sc.nextInt();
            System.out.println("1-usd , 2-eur, 3-gbp, 4-jpy, 5-chp, 6-cad,7-aud,8-cny");
            System.out.print("to: ");
            int u = sc.nextInt();
            System.out.print("amount: ");
            int m = sc.nextInt();
            PreparedStatement statement3 = connection.prepareStatement("select * from tbl2 where one=" + y);
            ResultSet resultSet1 = statement3.executeQuery();
            while (resultSet1.next()) {
                System.out.println(resultSet1.getDouble(u + 1) * m);
                break;
            }
        } else if (w == 1) {
            System.out.println("1-usd , 2-eur, 3-gbp, 4-jpy, 5-chf, 6-cad,7-aud,8-cny,9-tg");
            int y = sc.nextInt();
            if (y < 10) {
                PreparedStatement statement3 = connection.prepareStatement("select * from tbl2 where one=?");
                statement3.setInt(1, (int) user.getOne());
                ResultSet resultSet1 = statement3.executeQuery();
                user.setOne(y);
                while (resultSet1.next()) {
                    this.setRes(resultSet1.getDouble(y + 1) * user.getMoney());
                    user.setMoney(resultSet1.getDouble(y+1)*user.getMoney());
                    PreparedStatement statement6= connection.prepareStatement("select str from tbl3 where one=?");
                    statement6.setInt(1,(int)user.getOne());
                    ResultSet resultSet2=statement6.executeQuery();
                    while (resultSet2.next()){
                        this.setStr(resultSet2.getString(1));
                    }
                    PreparedStatement statement7= connection.prepareStatement("update userAccount1 set currency=concat(?,?),money=? where phoneNumber=?");
                    statement7.setDouble(1,this.getRes());
                    statement7.setString(2,this.getStr());
                    statement7.setDouble(3,user.getMoney());
                    statement7.setString(4,user.getPhoneNumber());
                    statement7.executeUpdate();
                    PreparedStatement statement8= connection.prepareStatement("select currency,money from userAccount1 where phoneNumber=? and password=?");
                    statement8.setString(1, user.getPhoneNumber());
                    statement8.setString(2, user.getPassword());
                    ResultSet resultSet3 = statement8.executeQuery();
                    while (resultSet3.next()){
                        user.setCurrency(resultSet3.getString(1));
                        user.setMoney(resultSet3.getDouble(2));
                    }
                    break;
                }
                System.out.println("current balance: " + user.getCurrency());
            } else {
                System.out.println("try again: ");
                this.currency(user);
            }
            statement2.executeUpdate();
        }
    }
}
