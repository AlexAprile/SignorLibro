package http;

import Storage.Entity.Utente;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class ErrorHandler {
    public ErrorHandler(){super();}
    //si fa il controllo se la sessione esiste o che l'account è presente in sessione
    public void authenicate(HttpSession session) throws  InvalidRequestException {
        if(session==null||session.getAttribute("utente")==null)
            throw new InvalidRequestException("Errore autorizzazione",List.of("Azione non consentita"), HttpServletResponse.SC_UNAUTHORIZED);
    }


    public void authorize(HttpSession session) throws  InvalidRequestException {
        authenicate(session);//in caso authenticate non lancia l'eccezione controlla che quell'account è un admin
        Utente accountSession=(Utente) session.getAttribute("utente");
        if(accountSession.isAdmin()!=true)
            throw new InvalidRequestException("Errore autorizzazione",List.of("Azione non consentita"), HttpServletResponse.SC_FORBIDDEN);
    }
}
