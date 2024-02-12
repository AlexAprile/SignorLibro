package Application;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/registrazione-controller")
public class RegistrazioneController extends HttpServlet {

    /**
     * Reindirizza alla pagina del form di registrazione se Utente ancora non registrato
     * Altrimenti se form mandato, allora viene registrato
     * */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address;

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente == null){

            if(request.getParameter("utente") == null) //non registarto
                address = "./WEB-INF/Interface/Registrazione/registrazione.jsp";
            else{ //registrazione dell'utente
                address = registraUtente(request);
            }
        }
        else {
            address = "./WEB-INF/account.jsp"; //mandato alla pagina dell'account
        }

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Prende request in input
     * Restituisce address alla pagina di successo
     * dopo aver controllato che tutti i parametri di registrazione rispettino le regole previste
     * altrimenti pagina registrazione con messaggio di errore
     * */
    private String registraUtente(HttpServletRequest request) {
        String address;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataNascita = request.getParameter("nascita");
        Date nascita = null;

        Utente utente;

        String errore = "";


        if(nome.length()<1 || nome.length()>50)
            errore += "Nome digitato troppo breve o troppo lungo";

        if(cognome.length()<1 || cognome.length()>50)
            errore += "Cognome digitato troppo breve o troppo lungo";

        if(email.length()<8 || cognome.length()>100)
            errore += "Email digitata troppo breve o troppo lunga";

        if(password.length()<4 || password.length()>50)
            errore += "Password digitata troppo breve o troppo lunga";

        if(email.length()<4 || email.length()>100)
            errore += "Email digitata troppo breve o troppo lunga";

        //data di nasdcita import java.util.Date;


        if(!errore.equals(""))
        {
            // I dati non sono validi, mostra la pagina di registrazione con il messaggio di errore
            address = "./WEB-INF/Interface/Registrazione/registrazione.jsp";
            request.setAttribute("errore", errore);
            return address;
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
