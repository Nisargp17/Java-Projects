public class TransactionTask extends Thread {
    private Account account;
    private boolean deposit;
    private double amount;

    public TransactionTask(Account account, boolean deposit, double amount) {
        this.account = account;
        this.deposit = deposit;
        this.amount = amount;
    }

    public void run() {
        try {
            if (deposit) {
                account.deposite(amount);
                System.out.println("Deposited " + amount + " to " + account.getaccountNumber());
            } else {
                account.withdraw(amount);
                System.out.println("Withdrew " + amount + " from " + account.getaccountNumber());
            }
        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }
}
