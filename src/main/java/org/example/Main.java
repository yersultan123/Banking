package org.example;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        HomePage homePage = new HomePage();
        MyApp myApp = new MyApp();
        while(!homePage.k){
            myApp.app();
        }
    }
}