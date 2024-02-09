package Storage.DAO;

import Storage.ConPool;
import Storage.Entity.Prodotto;

import java.sql.*;
import java.util.ArrayList;

public class ProdottoDAO {

    public ArrayList<Prodotto> cercaPerTitolo(String titolo) throws SQLException {
        try(Connection connection= ConPool.getConnection()) {
            String query = "SELECT *  FROM Prodotto AS pro WHERE (pro.titolo=?);";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, titolo);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor extractor = new ProductExtractor();
                ArrayList<Prodotto> products = new ArrayList<>();
                while (rs.next()) {
                    prodotto = extractor.extract(rs);
                    products.add(prodotto);
                }
                return products;
            }
        }
    }

    public Prodotto cercaPerISBN(String ISBN) throws SQLException {
        try(Connection connection= ConPool.getConnection()) {
            String query = "SELECT *  FROM Prodotto AS pro WHERE (pro.ISBN=?);";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, ISBN);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                if(rs.next()){
                    prodotto=new Prodotto();
                    prodotto.setId(rs.getInt("ID"));
                    prodotto.setTitolo(rs.getString("titolo"));
                    prodotto.setAutore(rs.getString("autore"));
                    prodotto.setIsbn(rs.getString("is"));
                    prodotto.setPrezzo(rs.getDouble("prezzo"));
                    prodotto.setDescrizione(rs.getString("descrizione"));
                    prodotto.setCategoria(rs.getString("categoria"));
                    prodotto.setQuantita(rs.getInt("quantita"));
                    prodotto.setDataPubblicazione(rs.getDate("dara_pubblicazione"));
                    prodotto.setCopertina(rs.getString("copertina"));
                }
                return prodotto;
            }
        }
    }

    public ArrayList<Prodotto> cercaTuttiProdotti() throws SQLException {
        try(Connection connection= ConPool.getConnection()) {
            String query = "SELECT *  FROM Prodotto;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor extractor = new ProductExtractor();
                ArrayList<Prodotto> products = new ArrayList<>();
                while (rs.next()) {
                    prodotto = extractor.extract(rs);
                    products.add(prodotto);
                }
                return products;
            }
        }
    }

    public boolean creaProdotto(Prodotto product) throws SQLException {

        try(Connection connection=ConPool.getConnection()) {
            String query1 = "INSERT INTO Prodotto(id, titolo, autore, ISBN, prezzo, data_pubblicazione,descrizione,copertina,categoria,Quantita) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?);";

            try (PreparedStatement ps1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setInt(1, product.getId());
                ps1.setString(2, product.getTitolo());
                ps1.setString(3, product.getAutore());
                ps1.setString(4, product.getIsbn());
                ps1.setDouble(5, product.getPrezzo());
                ps1.setDate(6, (Date) product.getDataPubblicazione());
                ps1.setString(7, product.getDescrizione());
                ps1.setString(8, product.getCopertina());
                ps1.setString(9, product.getCategoria());
                ps1.setInt(10, product.getQuantita());

                int rows = ps1.executeUpdate();

                ResultSet rs = ps1.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                product.setId(id);

                return rows == 1;
            }
        }
    }

    public boolean eliminaProdotto(int id) throws SQLException {

        try(Connection connection=ConPool.getConnection()) {
            String query = "DELETE FROM prodotto WHERE id=?; ";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,id);

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }

    }


}
