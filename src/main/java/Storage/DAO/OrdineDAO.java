package Storage.DAO;

import Storage.ConPool;
import Storage.Entity.Ordine;

import java.sql.*;

public class OrdineDAO {

    public boolean createOrder(Ordine order) throws SQLException {
            try (Connection connection = ConPool.getConnection()) {
            String query1 = "INSERT INTO Ordine(IdUser,dataOrdine) VALUES (?,?);";
            String query2 = "INSERT INTO order_product(id_ordine, id_prodotto, prezzo) VALUES (?,?,?);";
            connection.setAutoCommit(false);

            try (PreparedStatement ps1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, order.getEmailUtente());
                ps1.setDate(2, order.getDataOrdine());
                int rows = ps1.executeUpdate();

                ResultSet rs = ps1.getGeneratedKeys();
                rs.next();


                try (PreparedStatement psAssoc = connection.prepareStatement(query2)) {

                    int total = rows;

                    for (CartItem item : order.getCart().getCartItems()) {
                        psAssoc.setInt(1, order.getId());
                        psAssoc.setInt(2, item.getItem().getId());
                        psAssoc.setDouble(3,item.getItem().getPrice());
                        total += psAssoc.executeUpdate();

                    }

                    if (total == (rows + order.getNum_product())) {
                        connection.commit();
                        connection.setAutoCommit(true);
                        return true;
                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                }
            }
        }

    }
}
