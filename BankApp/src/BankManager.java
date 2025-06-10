import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BankManager {
    private static BankManager instance;
    private Map<String, Customer> customerMap;

    private BankManager() {
        customerMap = new HashMap<>();
    }

    public static synchronized BankManager getInstance() {
        if (instance == null) {
            instance = new BankManager();
        }
        return instance;
    }

    public Customer createCustomer(String customerId, String name) {
        Customer customer = new Customer(customerId, name);
        customerMap.put(customerId, customer);
        return customer;
    }

    public Customer getCustomer(String customerId) {
        return customerMap.get(customerId);
    }

    public Collection<Customer> getAllCustomers() {
        return customerMap.values();
    }
}