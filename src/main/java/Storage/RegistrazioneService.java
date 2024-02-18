package Storage;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import http.AccountValidator;
import http.AccountValidatorUpdate;
import http.Controller;
import http.InvalidRequestException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date nascita = new Date();
        try {
            nascita = dateFormat.parse(dataNascita);
        } catch (ParseException e) {
            // Gestione dell'eccezione in caso di formato di data non valido
            e.printStackTrace();
        }


        Utente utente;

        request.setAttribute("back","/WEB-INF/Interface/Registrazione/registrazione.jsp");
        try {
            Controller.validate(AccountValidatorUpdate.validateUpForm(request,email,nome,cognome,password,nascita));
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

        address = "/SignorLibro_war/AutenticazioneController/profile";
        request.setAttribute("utente", utente);
        request.getSession().setAttribute("utente", utente);

        return address;
    }
}
