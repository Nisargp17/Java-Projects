import model.Product;
import dao.ProductDAO;
import services.InventoryService;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryService service = new InventoryService();
        ProductDAO productDAO = new ProductDAO();

        while (true) {
            System.out.println("\n===== Inventory Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Purchase Product");
            System.out.println("3. Sell Product");
            System.out.println("4. List All Products");
            System.out.println("5. View Stock Valuation");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();

                        Product p = new Product();
                        p.setName(name);
                        p.setPrice(price);
                        p.setQuantity(qty);
                        productDAO.addProduct(p);
                        System.out.println("✅ Product added successfully.");
                        break;

                    case 2:
                        System.out.print("Product ID: ");
                        int pidPurchase = sc.nextInt();
                        System.out.print("Quantity: ");
                        int qtyPurchase = sc.nextInt();
                        service.purchaseProduct(pidPurchase, qtyPurchase);
                        System.out.println("✅ Purchase recorded.");
                        break;

                    case 3:
                        System.out.print("Product ID: ");
                        int pidSell = sc.nextInt();
                        System.out.print("Quantity: ");
                        int qtySell = sc.nextInt();
                        service.sellProduct(pidSell, qtySell);
                        System.out.println("✅ Sale recorded.");
                        break;

                    case 4:
                        List<Product> products = service.listAllProducts();
                        System.out.println("\n--- Product List ---");
                        for (Product product : products) {
                            System.out.printf("ID: %d | Name: %s | Price: %.2f | Quantity: %d\n",
                                    product.getId(), product.getName(), product.getPrice(), product.getQuantity());
                        }
                        break;

                    case 5:
                        double valuation = service.calculateStockValuation();
                        System.out.printf("Total Stock Valuation: $%.2f\n", valuation);
                        break;

                    case 6:
                        System.out.println("Exiting... Goodbye!");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
