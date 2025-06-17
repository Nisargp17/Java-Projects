package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Sale;

public class SaleDAO {
    public void addSale(Connection conn, Sale sale) throws Exception {
        String sql = "INSERT INTO Sale (product_id, quantity) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sale.getProductId());
            stmt.setInt(2, sale.getQuantity());
            stmt.executeUpdate();
        }
    }
}
