package Storage;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import http.ErrorHandler;
import http.InvalidRequestException;
import jakarta.servlet.http.HttpSession;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Classe che gestisce l'autenticazione degli utenti.
 */
public class AutenticazioneService {

    /**
     * Effettua il login dell'utente utilizzando l'email e la password fornite.
     *
     * @param email l'email dell'utente
     * @param password la password dell'utente
     * @return l'oggetto Utente corrispondente all'account autenticato, o null se l'autenticazione fallisce
     * @throws SQLException se si verifica un errore durante l'accesso al database
     * @throws NoSuchAlgorithmException se si verifica un errore durante l'algoritmo di hashing della password
     */
    public Utente login(String email, String password) throws SQLException, NoSuchAlgorithmException {
        UtenteDAO dao = new UtenteDAO();
        Utente utente = null;
        if (!email.isEmpty() && !password.isEmpty())
            utente = dao.fetchAccountWithPsw(email, password);
        return utente;
    }

    /**
     * Effettua il logout dell'utente dalla sessione specificata.
     *
     * @param session la sessione dell'utente
     * @throws RuntimeException se si verifica un errore durante l'autenticazione della sessione
     */
    public void logout(HttpSession session) {
        ErrorHandler service = new ErrorHandler();
        try {
            service.authenicate(session);
        } catch (InvalidRequestException e) {
            throw new RuntimeException(e);
        }
        Utente utente = (Utente) session.getAttribute("utente");
        session.removeAttribute("utente");
    }
}