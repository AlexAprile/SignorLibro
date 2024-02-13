package Storage;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import http.ErrorHandler;
import http.InvalidRequestException;
import jakarta.servlet.http.HttpSession;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class AutenticazioneService {

    public Utente login(String email,String password) throws SQLException, NoSuchAlgorithmException {
        UtenteDAO dao=new UtenteDAO();
        Utente utente=null;
        if(!email.isEmpty()&&!password.isEmpty())
            utente=dao.fetchAccountWithPsw(email, password);
        return utente;
    }

    public void logout(HttpSession session){
        ErrorHandler service=new ErrorHandler();
        try {
            service.authenicate(session);
        } catch (InvalidRequestException e) {
            throw new RuntimeException(e);
        }
        Utente utente= (Utente) session.getAttribute("utente");
        session.removeAttribute("utente");
    }
}
