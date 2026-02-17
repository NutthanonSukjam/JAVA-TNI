package Banking;

import java.io.*;
import java.util.*;

public class AccountTransaction extends BankAccount implements Transactionable {

    public AccountTransaction(String acctId) {
        super(acctId);
    }

    public boolean hasAccount() {

        try (Scanner sc = new Scanner(new File(filename))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");

                if (data[0].equals(acctId)) {
                    accBalance = Double.parseDouble(data[1]);
                    return true;
                }
            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }

    @Override
    public void deposit(double amount) {

        if (hasAccount()) {
            accBalance += amount;
            updateFile();
        }
    }

    @Override
    public void withdraw(double amount) {

        if (hasAccount()) {
            if (accBalance >= amount) {
                accBalance -= amount;
                updateFile();
            }
        }
    }

    @Override
    public double checkBalance() {

        if (hasAccount()) {
            return accBalance;
        }

        return 0;
    }

    // อัปเดตข้อมูลในไฟล์
    private void updateFile() {

        try {
            File inputFile = new File(filename);
            File tempFile = new File("temp.txt");

            Scanner sc = new Scanner(inputFile);
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                String[] data = line.split(",");

                if (data[0].equals(acctId)) {
                    pw.println(acctId + "," + accBalance);
                } else {
                    pw.println(line);
                }
            }

            sc.close();
            pw.close();

            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (Exception e) {
            System.out.println("Error updating file");
        }
    }
}

