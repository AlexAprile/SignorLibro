package Application;

import Storage.AutenticaztoneService;
import Storage.DAO.CarrelloDAO;
import Storage.DAO.UtenteDAO;
import Storage.Entity.Carrello;
import Storage.Entity.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AutenticazioneController extends HttpServlet {
    String address;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        address = "";
        AutenticaztoneService service=new AutenticaztoneService();
        try {
            Utente utente = service.login(email,password);
            if (utente!=null){   // the user is in the db then s/he is registered and can go on
                // Salva l'utente nella sessione
                HttpSession session = req.getSession();
                session.setAttribute("account", utente);
                // Redirect alla home page dell'utente
                if(utente.isAdmin()){
                    address="dashboard";
                }
                else {
                    Carrello carrello= (Carrello) session.getAttribute("cartSession");
                    if(carrello!=null&& (!utente.isAdmin()) ){
                        CarrelloDAO dao = new CarrelloDAO();
                        dao.addCart(carrello,utente.getMail());
                        session.removeAttribute("cartSession");
                    }
                    address="home";
                }
            } else {

                // Redirect alla pagina con un messaggio di errore
                address ="signin?error=1";  // con redirect, la pagina non può essere in WEB-INF, il browser non potrebbe raggiungerla
            }
            resp.sendRedirect(address);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(address);
    }
}
