import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Serializable {

    protected String accountNumber;
    protected double balance;
    protected List<Transaction> transactions;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public abstract String getaccountType();

    public synchronized void deposite(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public synchronized void withdraw(double amount) throws Exception {
        if (balance < amount) {
            throw new Exception("Insufficient funds");
        } else {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount));
        }
    }

    public String getaccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
