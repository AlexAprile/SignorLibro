package Storage.DAO;

import Storage.ConPool;
import Storage.ConPoolFacade;
import Storage.Entity.Utente;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe che gestisce le operazioni di accesso e manipolazione dei dati degli utenti nel database.
 */
public class UtenteDAO {
    /**
     * restituisce gli account presenti nel DB
      * @return
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */

    /**
     * Recupera tutti gli account utente dal database.
     *
     * @return un ArrayList contenente tutti gli account utente presenti nel database
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     * @throws NoSuchAlgorithmException se si verifica un errore durante l'uso di un algoritmo di hash
     */
    public static ArrayList<Utente> fetchUsers() throws SQLException, NoSuchAlgorithmException {
        try(Connection conn= ConPoolFacade.getConnection()){
            try(PreparedStatement ps=conn.prepareStatement("SELECT * FROM user;")){
                ResultSet rs= ps.executeQuery();
                ArrayList<Utente> accounts=new ArrayList<>();

                while(rs.next()){
                    //in questo ciclo vengono presi tutti gli account presenti nel
                    //database se non è un admin l'utente corrente
                    //viene usato il costruttore completo altimenti
                    //quello con solo email, password e admin
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
    /**
     * Recupera un account utente dal database utilizzando l'indirizzo email.
     *
     * @param email l'indirizzo email dell'account utente da recuperare
     * @return l'oggetto Utente corrispondente all'indirizzo email specificato
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     * @throws NoSuchAlgorithmException se si verifica un errore durante l'uso di un algoritmo di hash
     */
    public Utente fetchUser(String email) throws SQLException, NoSuchAlgorithmException {
        try(Connection conn= ConPoolFacade.getConnection()){
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

    /**
     * Recupera un account utente dal database utilizzando l'indirizzo email e la password.
     *
     * @param email l'indirizzo email dell'account utente
     * @param psw la password dell'account utente
     * @return l'oggetto Utente corrispondente all'indirizzo email e alla password specificati
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     * @throws NoSuchAlgorithmException se si verifica un errore durante l'uso di un algoritmo di hash
     */
    public Utente fetchAccountWithPsw(String email,String psw) throws SQLException, NoSuchAlgorithmException {
        try(Connection conn= ConPoolFacade.getConnection()){
            try(PreparedStatement ps=conn.prepareStatement("SELECT * FROM user WHERE email=? AND Password=?")){
                ps.setString(1,email);
                ps.setString(2,psw);
                ResultSet rs= ps.executeQuery();
                if(rs.next()){
                    Utente user = new Utente(rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getString("Nome"),
                            rs.getString("Cognome"),
                            rs.getDate("Data_di_nascita"),
                            rs.getBoolean("Amministratore"));
                    return user;
                }
                return null;
            }
        }
    }
    /**
     * Crea un nuovo account utente nel database.
     *
     * @param user l'oggetto Utente da creare
     * @return il numero di righe modificate nel database
     */
    public Integer createUser(Utente user){
        try (Connection conn= ConPoolFacade.getConnection()){
            try (PreparedStatement ps= conn.prepareStatement("INSERT INTO user (Email,Password,Nome,Cognome, Data_di_nascita,Amministratore) VALUES (?,?,?,?,?,?);")){
                ps.setString(1,user.getMail());
                ps.setString(2,user.getPassword());
                ps.setString(3,user.getNome());
                ps.setString(4, user.getCognome());
                ps.setDate(5, (Date) user.getNascita());
                ps.setBoolean(6, user.isAdmin());
                return ps.executeUpdate();
            }
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }

    }
    /**
     * Elimina un account utente dal database utilizzando l'indirizzo email.
     *
     * @param email l'indirizzo email dell'account utente da eliminare
     * @return il numero di righe modificate nel database
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     */
    public Integer deleteUser(String email) throws SQLException {
        try (Connection conn= ConPoolFacade.getConnection()){
            try (PreparedStatement ps= conn.prepareStatement("DELETE FROM user WHERE Email=?")){
                ps.setString(1,email);
                return ps.executeUpdate();
            }
        }
    }
    /**
     * Aggiorna un account utente nel database.
     *
     * @param utente l'oggetto Utente da aggiornare
     * @return il numero di righe modificate nel database
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     */

    public Integer updateUtente(Utente utente) throws SQLException {
        try (Connection conn= ConPoolFacade.getConnection()){
            try (PreparedStatement ps= conn.prepareStatement("UPDATE  account SET nome=?, cognome=?, password=? WHERE email=?")){
                ps.setString(1, utente.getNome());
                ps.setString(2,utente.getCognome());
                ps.setString(3,utente.getPassword());

                return ps.executeUpdate();
            }
        }
    }
    /**
     * Recupera tutti gli account utente non amministratori dal database.
     *
     * @return un ArrayList contenente tutti gli account utente non amministratori presenti nel database
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     */
    public ArrayList<Utente> searchAllAccount() throws SQLException {


        try(Connection connection=ConPoolFacade.getConnection()) {
            String query="SELECT * FROM user AS us WHERE amministratore=0;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();

                ArrayList<Utente> accounts = new ArrayList<>();
                Utente account = null;
                UtenteExtractor accountExtractor = new UtenteExtractor();

                while (rs.next()) {
                    account = accountExtractor.extract(rs);
                    accounts.add(account);
                }
                return accounts;
            }
        }
    }


}
