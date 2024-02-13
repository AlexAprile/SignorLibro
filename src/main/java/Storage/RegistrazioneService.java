package Storage;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import http.AccountValidator;
import http.Controller;
import http.InvalidRequestException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class RegistrazioneService {
    /**
     * Prende request in input
     * Restituisce address alla pagina di successo
     * dopo aver controllato che tutti i parametri di registrazione rispettino le regole previste
     * altrimenti pagina registrazione con messaggio di errore
     * */
    public String registraUtente(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidRequestException {
        String address;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataNascita = request.getParameter("nascita");
        Date nascita = new Date(dataNascita);

        Utente utente;

        String errore = "";


        /*if(nome.length()<1 || nome.length()>50)
            errore += "Nome digitato troppo breve o troppo lungo";

        if(cognome.length()<1 || cognome.length()>50)
            errore += "Cognome digitato troppo breve o troppo lungo";

        if(email.length()<8 || cognome.length()>100)
            errore += "Email digitata troppo breve o troppo lunga";

        if(password.length()<4 || password.length()>50)
            errore += "Password digitata troppo breve o troppo lunga";

        if(email.length()<4 || email.length()>100)
            errore += "Email digitata troppo breve o troppo lunga";*/

        //data di nascita import java.util.Date;



        /*if(!errore.equals(""))
        {
            // I dati non sono validi, mostra la pagina di registrazione con il messaggio di errore
            address = "./WEB-INF/Interface/Registrazione/registrazione.jsp";
            request.setAttribute("errore", errore);
            return address;
        }*/
        request.setAttribute("back","/WEB-INF/Interface/Registrazione/registrazione.jsp");
        try {
            Controller.validate(AccountValidator.validateUpForm(request));
        } catch (InvalidRequestException e) {
            try {
                e.handle(request,response);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        utente = new Utente();

        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setMail(email);
        utente.setPassword(password);
        utente.setNascita(nascita);

        UtenteDAO cdao = new UtenteDAO();
        cdao.createUser(utente);

        address = "./WEB-INF/pagine/successo.jsp";
        request.setAttribute("utente", utente);
        request.getSession().setAttribute("utente", utente);

        return address;
    }
}
