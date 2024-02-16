package Storage.DAO;

import Storage.ConPool;
import Storage.Entity.Prodotto;
import Storage.Entity.ProdottoCarrello;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            String query = "SELECT *  FROM Prodotto AS pro;";
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
/*
    public ArrayList<Prodotto> cercaTuttiProdotti() {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto");
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();

            while (rs.next()) {

                Prodotto p = new Prodotto();
                p.setId(rs.getInt("ID"));
                p.setTitolo(rs.getString("titolo"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setCopertina(rs.getString("copertina"));
                p.setQuantita(rs.getInt("quantita"));
                p.setIsbn(rs.getString("isbn"));
                p.setDataPubblicazione(rs.getDate("data_pubblicazione"));
                p.setCategoria(rs.getString("categoria"));
                prodotti.add(p);
            }

            return prodotti;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }*/

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
    public Integer updateProduct(Prodotto prodotto) throws SQLException {
        Connection con=ConPool.getConnection();
        PreparedStatement ps=con.prepareStatement("UPDATE prodotti SET  Titolo=?,Autore=?, Prezzo=?,descrizione=?,Coperina=?, quantita=? WHERE ID=?");
        ps.setString(1,prodotto.getTitolo());
        ps.setString(2, prodotto.getAutore());
        ps.setDouble(3,prodotto.getPrezzo());
        ps.setString(4, prodotto.getDescrizione());
        ps.setString(5,prodotto.getCategoria());
        ps.setInt(6,prodotto.getQuantita());
        ps.setInt(7,prodotto.getId());
        return ps.executeUpdate();
    }

    public Integer updateProductQ(ProdottoCarrello pc, Prodotto prodotto) throws SQLException {
        Connection con=ConPool.getConnection();
        PreparedStatement ps=con.prepareStatement("UPDATE prodotti SET quantita=? WHERE ID=?");
        ps.setInt(1,pc.getQuantita()-1);
        ps.setInt(2,prodotto.getId());
        return ps.executeUpdate();
    }

    public ArrayList<Prodotto> search(String value) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from prodotto WHERE titolo LIKE ?");
            ps.setString(1,value+"%");
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();

            while (rs.next()) {

                Prodotto p = new Prodotto();
                p.setId(rs.getInt("ID"));
                p.setTitolo(rs.getString("titolo"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setCopertina(rs.getString("copertina"));
                p.setQuantita(rs.getInt("quantita"));
                p.setIsbn(rs.getString("isbn"));
                p.setDataPubblicazione(rs.getDate("data_pubblicazione"));
                p.setCategoria(rs.getString("categoria"));
                prodotti.add(p);
            }

            return prodotti;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

}
