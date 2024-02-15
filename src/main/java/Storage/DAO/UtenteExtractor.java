package Storage.DAO;

import Storage.Entity.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteExtractor {
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
