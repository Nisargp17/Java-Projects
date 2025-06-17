package services;

import dao.ProductDAO;
import dao.PurchaseDAO;
import dao.SaleDAO;
import util.DBConnection;
import model.Product;
import model.Purchase;
import model.Sale;

import java.sql.Connection;
import java.util.List;

public class InventoryService {
    private ProductDAO productDAO = new ProductDAO();
    private PurchaseDAO purchaseDAO = new PurchaseDAO();
    private SaleDAO saleDAO = new SaleDAO();

    public void purchaseProduct(int productId, int quantity) throws Exception {
        Connection conn = DBConnection.getConnection();
        try {
            conn.setAutoCommit(false);
            Product product = productDAO.getProductById(productId);
            if (product == null) {
                throw new Exception("Product not found!");
            }
            int newQty = product.getQuantity() + quantity;
            productDAO.updateQuantity(productId, newQty);

            Purchase purchase = new Purchase();
            purchase.setProductId(productId);
            purchase.setQuantity(quantity);
            purchaseDAO.addPurchase(conn, purchase);

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    public void sellProduct(int productId, int quantity) throws Exception {
        Connection conn = DBConnection.getConnection();
        try {
            conn.setAutoCommit(false);
            Product product = productDAO.getProductById(productId);
            if (product == null)
                throw new Exception("Product not found!");
            if (product.getQuantity() < quantity)
                throw new Exception("Insufficient stock!");

            int newQty = product.getQuantity() - quantity;
            productDAO.updateQuantity(productId, newQty);

            Sale sale = new Sale();
            sale.setProductId(productId);
            sale.setQuantity(quantity);
            saleDAO.addSale(conn, sale);

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
    }

    public List<Product> listAllProducts() throws Exception {
        return productDAO.getAllProducts();
    }

    public double calculateStockValuation() throws Exception {
        double total = 0;
        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            total += p.getPrice() * p.getQuantity();
        }
        return total;
    }

}