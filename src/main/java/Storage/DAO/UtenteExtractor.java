package Storage.DAO;

import Storage.Entity.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe utilizzata per estrarre i dati di un oggetto Utente da un ResultSet.
 */
public class UtenteExtractor {
    /**
     * Estrae i dati di un Utente da un ResultSet.
     *
     * @param resultSet il ResultSet contenente i dati dell'Utente
     * @return un oggetto Utente con i dati estratti dal ResultSet
     * @throws SQLException se si verifica un errore durante l'accesso ai dati nel ResultSet
     */
    public Utente extract(ResultSet resultSet) throws SQLException {
        Utente account=new Utente();
        account.setAdmin(resultSet.getBoolean("us.amministratore"));
        account.setMail(resultSet.getString("us.email"));
        account.setNome(resultSet.getString("us.nome"));
        account.setCognome(resultSet.getString("us.cognome"));
        account.setNascita(resultSet.getDate("us.data_di_nascita"));
        //mettere le password criptate metodo prof lato query e lato metodo setPassword
        account.setPassword(resultSet.getString("us.password"));
        return account;
    }
}
