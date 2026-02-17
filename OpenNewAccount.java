package Banking;

import java.io.*;
import java.util.Scanner;

public class OpenNewAccount extends BankAccount {

    public OpenNewAccount(String acctId, double accBalance) {
        super(acctId, accBalance);
    }

    // เขียนข้อมูลลงไฟล์
    public String recordAccount() {

        if (isExistingAccount(acctId)) {
            return "This account has been created!";
        }

        try (FileWriter fw = new FileWriter(filename, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(this.toString());
            return "Created account success!!";

        } catch (IOException e) {
            return "Error";
        }
    }

    // ตรวจสอบว่ามีบัญชีอยู่แล้วหรือไม่
    public boolean isExistingAccount(String acctId) {

        try (Scanner sc = new Scanner(new File(filename))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");

                if (data[0].equals(acctId)) {
                    return true;
                }
            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }
}
