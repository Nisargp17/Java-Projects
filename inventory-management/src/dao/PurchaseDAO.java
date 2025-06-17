package dao;

import model.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PurchaseDAO {
    public void addPurchase(Connection conn, Purchase purchase) throws Exception {
        String sql = "INSERT INTO Purchase (product_id, quantity) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, purchase.getProductId());
            stmt.setInt(2, purchase.getQuantity());
            stmt.executeUpdate();
        }
    }
}
