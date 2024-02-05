package Storage.DAO;

import Storage.ConPool;
import Storage.Entity.Utente;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    /**
     * restituisce gli account presenti nel DB
      * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public List<Utente> fetchUsers() throws SQLException, NoSuchAlgorithmException {
        try(Connection conn= ConPool.getConnection()){
            try(PreparedStatement ps=conn.prepareStatement("SELECT * FROM user;")){
                ResultSet rs= ps.executeQuery();
                List<Utente> accounts=new ArrayList<>();

                while(rs.next()){
                    //in questo ciclo vengono presi tutti gli account presenti nel
                    //database se non è un admin l'utente corrente
                    //viene usato il costruttore completo altimenti
                    //quello con solo email,password e admin
                    if(!rs.getBoolean("Amministratore")) {
                        Utente user = new Utente(rs.getString("Email"),
                                rs.getString("Password"),
                                rs.getString("Nome"),
                                rs.getString("Cognome"),
                                rs.getDate("Data_di_nascita"),
                                rs.getBoolean("Amministratore"));
                        accounts.add(user);
                    }else {
                        Utente admin=new Utente(rs.getString("Email"),
                                rs.getString("Password"),
                                rs.getBoolean("Amministratore"));
                        accounts.add(admin);
                    }
                }
                rs.close();
                return accounts;
            }
        }
    }
    public Utente fetchUser(String email) throws SQLException, NoSuchAlgorithmException {
        try(Connection conn= ConPool.getConnection()){
            try(PreparedStatement ps=conn.prepareStatement("SELECT * FROM user WHERE Email=?")){
                ps.setString(1,email);
                ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    if(!rs.getBoolean("Amministratore")) {
                        Utente user = new Utente(rs.getString("Email"),
                                rs.getString("Password"),
                                rs.getString("Nome"),
                                rs.getString("Cognome"),
                                rs.getDate("Data_di_nascita"),
                                rs.getBoolean("Amministratore"));
                        return user;
                    }else {
                        Utente admin=new Utente(rs.getString("Email"),
                                rs.getString("Password"),
                                rs.getBoolean("Amministratore"));
                        return admin;
                    }
                }
                return null;
            }
        }
    }
    public Integer createUser(Utente user) throws SQLException {
        try (Connection conn= ConPool.getConnection()){
            try (PreparedStatement ps= conn.prepareStatement("INSERT INTO user (Email,Password,Nome,Cognome, Data_di_nascita,Amministratore) VALUES (?,?,?,?,?,?);")){
                ps.setString(1,user.getMail());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getNome());
                ps.setString(4, user.getCognome());
                ps.setDate(5, (Date) user.getNascita());
                ps.setBoolean(6, user.isAdmin());
                return ps.executeUpdate();
            }
        }
    }
    public Integer deleteUser(String email) throws SQLException {
        try (Connection conn= ConPool.getConnection()){
            try (PreparedStatement ps= conn.prepareStatement("DELETE FROM user WHERE Email=?")){
                ps.setString(1,email);
                return ps.executeUpdate();
            }
        }
    }
}
