package Storage.DAO;

import Storage.ConPool;
import Storage.Entity.Ordine;
import Storage.Entity.ProdottoCarrello;

import java.sql.*;
import java.util.ArrayList;

public class OrdineDAO {

    public boolean createOrder(Ordine order) throws SQLException {
            try (Connection connection = ConPool.getConnection()) {
            String query1 = "INSERT INTO Ordine(IdUser,dataOrdine) VALUES (?,?);";
            String query2 = "INSERT INTO ProdottoOrdine(idordine, idprodotto, Quantitaordine) VALUES (?,?,?);";
            connection.setAutoCommit(false);

            try (PreparedStatement ps1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, order.getEmailUtente());
                ps1.setDate(2, order.getDataOrdine());
                int rows = ps1.executeUpdate();

                ResultSet rs = ps1.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                order.setId(id);

                try (PreparedStatement psAssoc = connection.prepareStatement(query2)) {

                    int total = rows;

                    for (ProdottoCarrello item : order.getProdotti()) {
                        psAssoc.setInt(1, order.getId());
                        psAssoc.setInt(2, item.getProdotto().getId());
                        psAssoc.setInt(3,item.getProdotto().getQuantita());
                        total += psAssoc.executeUpdate();

                    }

                    if (total == (rows + order.getProdotti().size())) {
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

    public ArrayList<Ordine> searchAllOrder() throws SQLException {
        try(Connection connection=ConPool.getConnection()) {
            String query="SELECT * FROM ordine AS ord;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();

                ArrayList<Ordine> orders = new ArrayList<>();
                Ordine order = null;
                OrderExtractor orderExtractor = new OrderExtractor();

                while (rs.next()) {
                    order = orderExtractor.extract(rs);
                    orders.add(order);
                }
                return orders;
            }
        }
    }
}
