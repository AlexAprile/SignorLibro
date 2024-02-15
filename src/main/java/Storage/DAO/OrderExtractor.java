package Storage.DAO;

import Storage.Entity.Ordine;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderExtractor {
    public Ordine extract(ResultSet resultSet) throws SQLException {
        Ordine order=new Ordine();
        order.setId(resultSet.getInt("ord.id"));
        order.setEmailUtente(String.valueOf(resultSet.getInt("ord.iduser")));
        order.setDataOrdine(Date.valueOf(resultSet.getDate("ord.dataordine").toLocalDate()));
        return order;
    }
}
