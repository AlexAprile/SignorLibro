package Application;

import Storage.AutenticazioneService;
import Storage.DAO.CarrelloDAO;
import Storage.DAO.OrdineDAO;
import Storage.DAO.ProdottoDAO;
import Storage.DAO.UtenteDAO;
import Storage.Entity.Carrello;
import Storage.Entity.Ordine;
import Storage.Entity.Prodotto;
import Storage.Entity.Utente;
import http.ErrorHandler;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "AutenticazioneController", value = "/AutenticazioneController/*")
public class AutenticazioneController extends HttpServlet {
    String address;
    private  HttpSession session;

    RequestDispatcher dispatcher;
    ProdottoDAO sqlProductDao = new ProdottoDAO();
    UtenteDAO udao = new UtenteDAO();

    OrdineDAO sqlOrderDao = new OrdineDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        address = "";
        AutenticazioneService service=new AutenticazioneService();

        String  path=(req.getPathInfo()!=null) ? req.getPathInfo():"/";
        RequestDispatcher dispatcher;

        switch (path){
            case"/signin":
                address="/WEB-INF/Interface/Autenticazione/login.jsp";
                req.getRequestDispatcher(address).forward(req,resp);
                break;
            case "/profile":
                session.setAttribute("account", req.getSession().getAttribute("account"));
                address="/WEB-INF/Interface/profile.jsp";
                System.out.println(" sono in profile" );
                req.getRequestDispatcher(address).forward(req,resp);
                break;
            case"/accountsAdmin":
                ErrorHandler handler=new ErrorHandler();
                try {
                    handler.authenicate((HttpSession) req.getSession());
                    UtenteDAO dao=new UtenteDAO();
                    ArrayList<Utente> utenti=dao.searchAllAccount();
                    req.setAttribute("accounts",utenti);
                    req.getRequestDispatcher("/WEB-INF/Interface/accountAdmin.jsp").forward(req,resp);
                } catch (InvalidRequestException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/login":
                try {
                    Utente utente = service.login(email,password);
                    if (utente!=null){   // the user is in the db then s/he is registered and can go on
                        // Salva l'utente nella sessione
                        HttpSession session = req.getSession();
                        session.setAttribute("account", utente);
                        // Redirect alla home page dell'utente
                        if(utente.isAdmin()){
                            address="/WEB-INF/Interface/homeAdmin.jsp";
                        }
                        else {
                            Carrello carrello= (Carrello) session.getAttribute("cartSession");
                            if(carrello!=null&& (!utente.isAdmin()) ){
                                CarrelloDAO dao = new CarrelloDAO();
                                dao.addCart(carrello,utente.getMail());
                                session.removeAttribute("cartSession");
                            }
                            address="/homeUtente";
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

                dispatcher =req.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                dispatcher.forward(req,resp);
                break;
            case "/logout":
                req.getSession(false).invalidate();
                resp.sendRedirect("http://localhost:8080/SignorLibro_war/GestioneProdottoController/home");
                break;
            case "/homeAdmin":
                dispatcher= req.getRequestDispatcher("/WEB-INF/Interface/homeAdmin.jsp");
                dispatcher.forward(req,resp);
                break;
            case "/home":
                dispatcher= req.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
                dispatcher.forward(req,resp);
                break;
            case "/homeUtente":
                dispatcher= req.getRequestDispatcher("/WEB-INF/Interface/homeUtente.jsp");
                dispatcher.forward(req,resp);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {
            case "/login":
                String password = request.getParameter("password");
                String email = request.getParameter("email");


                /** se rientriamo nel sito con un altro account dobbiamo prima distruggere la sessione precendete **/

                Utente accountprova;

                accountprova = (Utente) request.getSession().getAttribute("account");
                if (accountprova != null) {
                    String pass = accountprova.getPassword();
                    String em = accountprova.getMail();
                    if (!(pass.equals(password) && em.equals(email))) {
                        request.getSession(false).invalidate();
                    }
                }


                 session = request.getSession(true);
                Utente account = null;
                try {

                    account = udao.fetchAccountWithPsw(email, password);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

                if (account == null) {
                    System.out.println("account non esistente");
                    dispatcher = request.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
                    dispatcher.forward(request, response);

                } else if (account.isAdmin()) {
                    try {
                        /* numero prodotti*/
                        ArrayList<Prodotto> products = sqlProductDao.cercaTuttiProdotti();
                        request.getSession(false).setAttribute("n_products", products.size());

                        /*numero utenti*/
                        ArrayList<Utente> accounts = udao.searchAllAccount();
                        request.getSession(false).setAttribute("n_client", accounts.size());

                        /*Totale incasso*/
                        ArrayList<Ordine> orders = sqlOrderDao.searchAllOrder();
                        ArrayList<Ordine> newOrdini = new ArrayList<>();
                        LocalDate now = LocalDate.now();

                        for (int i = 0; i < orders.size(); i++) {
                            //if (orders.get(i).getDataOrdine().getMonth().equals(now.getMonth()))
                                newOrdini.add(orders.get(i));

                        }

                        float totale = 0;
                        for (int j = 0; j < newOrdini.size(); j++) {
                            totale += newOrdini.get(j).getTotale();
                        }
                        request.getSession(false).setAttribute("totale_incasso", Math.round(totale * 100.0) / 100.0);

                        /*Numero ordini mensili*/
                        request.getSession(false).setAttribute("n_ordini", newOrdini.size());

                        System.out.println("account admin");

                        session.setAttribute("account", account);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/Interface/homeAdmin.jsp");
                        dispatcher.forward(request, response);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                } else if (!account.isAdmin()){
                    session.setAttribute("account", account);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/Interface/homeUtente.jsp");
                    dispatcher.forward(request, response);
                }


                break;
        }
    }
}
