package Storage;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Classe che gestisce le operazioni di gestione degli utenti.
 */
public class GestioneUtenteService {
    /**
     * Modifica i dati di un utente nel database.
     *
     * @param utente l'oggetto Utente contenente i dati da modificare
     * @return true se la modifica dei dati utente ha avuto successo, false altrimenti
     */
    public boolean modificaDatiUtente(Utente utente){
        UtenteDAO utenteDAO=new UtenteDAO();
        try {
            utenteDAO.updateUtente(utente);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    /**
     * Recupera tutti gli utenti dal database.
     *
     * @return un ArrayList contenente tutti gli utenti presenti nel database
     * @throws RuntimeException se si verifica un'eccezione di tipo SQLException o NoSuchAlgorithmException
     */
    public ArrayList<Utente> getAllUtenti(){
        UtenteDAO utenteDAO=new UtenteDAO();
        ArrayList<Utente> users= null;
        try {
            users = UtenteDAO.fetchUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
