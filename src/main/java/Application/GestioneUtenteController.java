package Application;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UtenteController", value = "/UtenteServlet/*")
public class GestioneUtenteController {
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private UtenteDAO accountDao = new UtenteDAO();
    private Utente account = new Utente();
    private UtenteDAO sqlAccountDao2 = new UtenteDAO();



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {

            case "/showUpdateUsername":
                account = (Utente) request.getSession(false).getAttribute("account");

                if (account.isAdmin() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentUsername.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            case "/showUpdateEmail":

                account = (Utente) request.getSession(false).getAttribute("account");

                if (account.isAdmin() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                    dispatcher.forward(request, response);
                } else {

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentEmail.jsp");
                    dispatcher.forward(request, response);

                }

                break;

            case "/showUpdatePassword":

                account = (Utente) request.getSession(false).getAttribute("account");
                if (account.isAdmin() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminPassword.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentPassword.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**la servlet vede se è gia presente un email con lo stesso indirizzo**/
            /** AJAX  nella registrazione **/
            case "/checkEmailSign":

                String email = request.getParameter("email");

                try {
                    account = accountDao.fetchUser(email);

                    if (account != null) {


                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println("Hey, sembra che l’indirizzo email corrisponda a un account già esistente.");


                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                break;

            /**si controlla che l email sia proprio quella dell account(controllo con ajax)**/
            /** nelle pagine di update **/


            case "/checkEmail":
                String email1 = request.getParameter("email");

                account = (Utente) request.getSession(false).getAttribute("account");
                boolean trovato = account.getMail().equals(email1);


                if (!trovato) {
                    try {
                        account = sqlAccountDao2.fetchUser(email1);
                        if (account != null) {
                            response.setContentType("text/plain;charset=UTF-8");
                            response.getWriter().println("Hey, sembra che l'indirizzo email corrisponda a un account già esistente.");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }

                }

                break;

            /**si visualizzano tutti gli utenti(ADMIN)**/

            case "/showAllUtent":

                ArrayList<Utente> accounts = new ArrayList<>();
                try {
                    accounts = (ArrayList<Utente>) UtenteDAO.fetchUsers();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("accounts", accounts);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/allUtent.jsp");
                dispatcher.forward(request, response);

                break;
        }


    }
}
