package Application;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import Storage.RegistrazioneService;
import http.AccountValidator;
import http.Controller;
import http.InvalidRequestException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@WebServlet("/registrazione-controller")
public class RegistrazioneController extends HttpServlet {

    /**
     * Reindirizza alla pagina del form di registrazione se Utente ancora non registrato
     * Altrimenti se form mandato, allora viene registrato
     * */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = null;

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente == null){

            if(request.getParameter("utente") == null) //non registarto
                address = "./WEB-INF/Interface/Registrazione/registrazione.jsp";
            else{ //registrazione dell'utente
                RegistrazioneService service=new RegistrazioneService();
                try {
                    address = service.registraUtente(request,response);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (InvalidRequestException e) {
                    e.handle(request, response);
                }
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

}
