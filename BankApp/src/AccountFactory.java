public class AccountFactory {
    public static Account createAccount(String type, String accountNumber) {
        switch (type.toLowerCase()) {
            case "saving":
                return new SavingsAccount(accountNumber);
            case "current":
                return new CurrentAccount(accountNumber);
            default:
                throw new IllegalArgumentException("Invalid account type: " + type);
        }

    }
}
