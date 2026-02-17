package Banking;

import java.util.Scanner;

public class BankSystem1 {

    static Scanner sc = new Scanner(System.in);

    public static String input_account_id() {

        String accId;

        while (true) {
            System.out.print("Enter account id: ");
            accId = sc.nextLine();

            if (accId.length() == 10) {
                return accId;
            } else {
                System.out.print("Enter account id: ");
                sc.nextLine();
            }
        }
    }

    public static double input_initial_balance() {

        while (true) {
            try {
                System.out.print("Enter your initial deposit amount: ");
                return Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Try again!! ");
            }
        }
    }


    public static void main(String[] args) {

        String acctId = input_account_id();

        double balance = input_initial_balance();

        OpenNewAccount newAcc = new OpenNewAccount(acctId, balance);

        String result = newAcc.recordAccount();

        System.out.println(result);
    }
}

