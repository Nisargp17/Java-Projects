public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public String getaccountType() {
        return "Savings";
    }
}
