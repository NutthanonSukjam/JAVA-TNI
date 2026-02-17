package Banking;

import Banking.AccountTransaction;
import java.util.Scanner;

public class BankSystem2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your bank account: ");
        String id = sc.nextLine();

        AccountTransaction account = new AccountTransaction(id);

        if (!account.hasAccount()) {
            System.out.println("Bank account not found...");
            return;
        }

        int menu;

        do {
            System.out.println("\nPress 1 to deposit");
            System.out.println("Press 2 to withdraw");
            System.out.println("Press 3 to check balance");
            System.out.println("Press 4 to exit");

            System.out.print("Enter a menu: ");
            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    System.out.println("Your balance = " + account.checkBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double wd = sc.nextDouble();
                    account.withdraw(wd);
                    System.out.println("Your balance = " + account.checkBalance());
                    break;

                case 3:
                    System.out.println("Your balance = " + account.checkBalance());
                    break;

                case 4:
                    System.out.println("Exit...");
                    break;

                default:
                    System.out.println("Invalid menu. Try again.");
            }

        } while (menu != 4);
    }
}