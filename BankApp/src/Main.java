import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankManager bankManager = BankManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Banking System Menu ---");
            System.out.println("1. Create Customer");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Report");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    bankManager.createCustomer(customerId, name);
                    break;

                case 2:
                    System.out.print("Enter Customer ID: ");
                    customerId = scanner.nextLine();
                    Customer customer = bankManager.getCustomer(customerId);
                    if (customer != null) {
                        System.out.print("Enter Account Type (savings/current): ");
                        String type = scanner.nextLine();
                        String accNumber = "ACC" + System.currentTimeMillis();
                        Account newAccount = AccountFactory.createAccount(type, accNumber);
                        customer.addAccount(newAccount);
                        System.out.println("Account created: " + newAccount.getaccountNumber());
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Customer ID: ");
                    customerId = scanner.nextLine();
                    System.out.print("Enter Account Number: ");
                    String accNo = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    processTransaction(bankManager, customerId, accNo, depositAmount, true);
                    break;

                case 4:
                    System.out.print("Enter Customer ID: ");
                    customerId = scanner.nextLine();
                    System.out.print("Enter Account Number: ");
                    accNo = scanner.nextLine();
                    System.out.print("Enter Amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    processTransaction(bankManager, customerId, accNo, withdrawAmount, false);
                    break;

                case 5:
                    for (Customer c : bankManager.getAllCustomers()) {
                        System.out.println("Customer: " + c.getName());
                        for (Account a : c.getAccounts()) {
                            System.out.println("  Account " + a.getaccountNumber() + " (" + a.getaccountType()
                                    + ") - Balance: " + a.getBalance());
                            for (Transaction t : a.getTransactions()) {
                                System.out.println("    " + t);
                            }
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }

        }
    }

    private static void processTransaction(BankManager bankManager, String customerId, String accNo, double amount,
            boolean isDeposit) {
        Customer customer = bankManager.getCustomer(customerId);
        if (customer != null) {
            for (Account acc : customer.getAccounts()) {
                if (acc.getaccountNumber().equals(accNo)) {
                    new TransactionTask(acc, isDeposit, amount).start();
                    return;
                }
            }
        }
        System.out.println("Account not found.");
    }
}