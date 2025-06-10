public class CurrentAccount extends Account {
    public CurrentAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public String getaccountType() {
        return "Current";
    }
}
