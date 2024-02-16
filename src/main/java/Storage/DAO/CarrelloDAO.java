package Storage.DAO;

import Storage.ConPool;
import Storage.ConPoolFacade;
import Storage.Entity.Carrello;
import Storage.Entity.Prodotto;
import Storage.Entity.ProdottoCarrello;
import Storage.Entity.ProdottoOrdine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarrelloDAO {
    public Carrello fetchCart(String email) {
        try (Connection conn = ConPoolFacade.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM carrello WHERE iduser=?;")) {
                ps.setString(1,email);
                ResultSet rs = ps.executeQuery();
                Carrello carrello = new Carrello();
                while (rs.next()) {
                    Prodotto prodotto=new Prodotto();
                    ProdottoDAO service=new ProdottoDAO();
                    prodotto=service.cercaPerISBN(rs.getString("idprodotti"));
                    carrello.aggiungiProdotto(prodotto,rs.getInt("quantita"));
                }
                rs.close();
                return carrello;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addCart(Carrello carrello,String email){
        try (Connection conn= ConPool.getConnection()){
            List<ProdottoCarrello> items=carrello.getCartItems();
            for(ProdottoCarrello item:items){
                addItem(item,email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCart(String email){
        try (Connection conn= ConPool.getConnection()){
            try (PreparedStatement ps= conn.prepareStatement("DELETE FROM carrello WHERE iduser=?")){
                ps.setString(1,email);
                ps.executeUpdate();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addItem(ProdottoCarrello item,String email){
        try (Connection conn= ConPool.getConnection()){
            List<ProdottoCarrello> items=fetchCart(email).getCartItems();
            for (ProdottoCarrello tmp:items)
                if(tmp.getProdotto().getId()==item.getProdotto().getId()){
                    PreparedStatement ps1= conn.prepareStatement("UPDATE  carrello SET quantita=? WHERE iduser=? AND idprodotti=?");
                    ps1.setInt(1,(item.getQuantita()+tmp.getQuantita()));
                    ps1.setString(2,email);
                    ps1.setInt(3,item.getProdotto().getId());
                    return ps1.executeUpdate();
                }
            PreparedStatement ps= conn.prepareStatement("INSERT INTO carrello (iduser,idprodotti) VALUES (?,?,?);");
            ps.setInt(2,item.getProdotto().getId());
            ps.setString(1,email);;
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer deleteItem(int id,String email) throws SQLException {
        try (Connection conn= ConPool.getConnection()){
            try (PreparedStatement ps= conn.prepareStatement("DELETE FROM carrello WHERE idprodotti=? AND iduser=?")){
                ps.setInt(1,id);
                ps.setString(2,email);
                return ps.executeUpdate();
            }
        }
    }
}
