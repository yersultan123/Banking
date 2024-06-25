package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class MyApp {
    User user = new User();
    HomePage homePage = new HomePage();
    Currency currency = new Currency();
    Transfer transfer = new Transfer();
    Replenishment replenishment =new Replenishment();
    static Scanner sc = new Scanner(System.in);
    void app() throws SQLException, IOException {
        System.out.println("      WELCOME");
        System.out.println("      SELECT:");
        System.out.println("1.Log in       2. Sign in");
        this.start();
        System.out.println("                              Main page                                 ");
        homePage.k=false;
        while(!homePage.k){
        this.main();}

    }
    void start() throws SQLException {
        int choice = sc.nextInt();
        if(choice==1){
            user.logIn();
        }
        else if(choice==2){
            user.createUser();
        }
        else{
            System.out.println("wrong choice, try again");
            this.start();
        }
    }
    void main() throws SQLException, IOException {
        System.out.println("1.Currency converter | 2.Transfer | 3.Replenishment | 4.Profile");
        int choice2 = sc.nextInt();
        switch (choice2){
            case 1:
                currency.currency(user);
                break;
            case 2:
                transfer.transfer(user);
                break;
            case 3:
                replenishment.replenishment(user);
                break;
            case 4:
                homePage.profileGeneral(user);
                break;
            default:
                System.out.println("wrong,try again");
                this.main();
        }
    }
}
