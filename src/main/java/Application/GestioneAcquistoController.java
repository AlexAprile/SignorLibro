package Application;

import Storage.DAO.OrdineDAO;
import Storage.Entity.Carrello;
import Storage.Entity.Ordine;
import Storage.Entity.Utente;
import Storage.GestioneAcquistiService;
import Storage.GestioneProdottoService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "GestioneAcquistoController", value = "/GestioneAcquistoController/*")
public class GestioneAcquistoController extends HttpServlet {
    private RequestDispatcher dispatcher;
    Ordine order = new Ordine();
    OrdineDAO orderDao = new OrdineDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        HttpSession session;
        String isbn;
        GestioneAcquistiService gas = new GestioneAcquistiService();

        switch (path){
            case"/showCartGuest":
                dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/carrello.jsp");
                dispatcher.forward(request,response);
                break;
            case"/showCartUtente":
                dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/carrelloUtente.jsp");
                dispatcher.forward(request,response);
                break;


            case"/addCart":
                isbn = request.getParameter("isbn");
                gas.aggiungiProdottoAlCarrello(isbn,request,response);
                //dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
                //dispatcher.forward(request,response);
                break;
            case "/addCartUtente":
                isbn = request.getParameter("isbn");
                gas.aggiungiProdottoAlCarrelloUtente(isbn,request,response);
                break;
            case "/rimuoviCarrello":
                isbn = request.getParameter("isbn");
                gas.rimuoviProdottoDalCarrello(isbn,request,response);
                //dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
                //dispatcher.forward(request,response);
                break;
            case "/rimuoviCarrelloUtente":
                isbn = request.getParameter("isbn");
                gas.rimuoviProdottoDalCarrelloUtente(isbn,request,response);
                break;
            case "/createOrder":
                Carrello cart= (Carrello) request.getSession(false).getAttribute("carrello");

                if(cart==null){
                    boolean carrellovuoto=true;
                    request.setAttribute("carrelloVuoto",carrellovuoto);

                    dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/homeUtente.jsp");
                    dispatcher.forward(request,response);

                }else {


                    LocalDate data = LocalDate.now();
                    Utente account1 = (Utente) request.getSession(false).getAttribute("account");
                    order.setDataOrdine(Date.valueOf(data));
                    order.setProdotti(cart.getCartItems());
                    order.setEmailUtente(account1.getMail());

                    try {
                        orderDao.createOrder(order);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    cart.getCartItems().clear();

                    request.getSession(false).setAttribute("carrello", cart);
                    request.getSession(false).setAttribute("totale", Math.round(cart.prezzoTotale()*100.0)/100.0);
                    request.getSession(false).setAttribute("quantity", cart.getCartItems().size());



                    dispatcher = request.getRequestDispatcher("/WEB-INF/Interface/homeUtente.jsp");
                    dispatcher.forward(request, response);
                }
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
