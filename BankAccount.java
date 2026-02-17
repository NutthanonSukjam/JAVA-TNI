package Banking;

public class BankAccount {

    protected String acctId;
    protected double accBalance;
    protected String filename = "users_account.txt";

    public BankAccount() {
    }

    public BankAccount(String acctId) {
        this.acctId = acctId;
    }

    public BankAccount(String acctId, double accBalance) {
        this.acctId = acctId;
        this.accBalance = accBalance;
    }

    public String getAcctId() {
        return acctId;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(double accBalance) {
        this.accBalance = accBalance;
    }

    @Override
    public String toString() {
        return acctId + "," + accBalance;
    }
}


