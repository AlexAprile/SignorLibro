package Storage.DAO;

import Storage.Entity.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExtractor {
    public Prodotto extract(ResultSet resultSet) throws SQLException {
        Prodotto product=new Prodotto();
        product.setId(resultSet.getInt("pro.id"));
        product.setPrezzo(resultSet.getDouble("pro.prezzo"));
        product.setDescrizione(resultSet.getString("pro.descrizione"));
        product.setIsbn(resultSet.getString("pro.isbn"));
        product.setAutore(resultSet.getString("pro.autore"));
        product.setDataPubblicazione(resultSet.getDate("pro.data_pubblicazione"));
        product.setCategoria(resultSet.getString("pro.categoria"));
        product.setQuantita(resultSet.getInt("pro.quantita"));
        product.setTitolo(resultSet.getString("pro.titolo"));
        product.setCopertina(resultSet.getString("pro.copertina"));
        return product;
    }
}
