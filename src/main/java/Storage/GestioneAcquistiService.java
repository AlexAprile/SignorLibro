package Storage;

import Storage.DAO.CarrelloDAO;
import Storage.DAO.OrdineDAO;
import Storage.DAO.ProdottoDAO;
import Storage.Entity.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Classe che gestisce le operazioni di acquisto e gestione del carrello.
 */
public class GestioneAcquistiService {
    RequestDispatcher dispatcher;
    /**
     * Aggiunge un prodotto al carrello dell'utente.
     *
     * @param ISBN_prodotto l'ISBN del prodotto da aggiungere al carrello
     * @param request la richiesta HTTP
     * @param response la risposta HTTP
     * @return true se il prodotto viene aggiunto con successo al carrello, false altrimenti
     */
    public boolean aggiungiProdottoAlCarrello(String ISBN_prodotto,HttpServletRequest request, HttpServletResponse response){

        try {
            ProdottoDAO pd = new ProdottoDAO();
            Prodotto prodotto = pd.cercaPerISBN(ISBN_prodotto);
            if(prodotto!=null){

                if(request.getSession(false).getAttribute("carrello")==null){

                    request.getSession(false).setAttribute("carrello",new Carrello());
                }
                Carrello cart= (Carrello) request.getSession().getAttribute("carrello");
                if(!cart.isPresent(prodotto)){

                    cart.aggiungiProdotto(prodotto,1);
                    request.getSession(false).setAttribute("carrello",cart);
                    request.getSession(false).setAttribute("totale",Math.round(cart.prezzoTotale()*100.0)/100.0);
                    request.getSession(false).setAttribute("quantity",prodotto.getQuantita());

                    //response.sendRedirect("/WEB-INF/Interface/index.jsp");

                }
                else{

                }
                dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
                dispatcher.forward(request,response);


            }

            else{
                /*pagina di errore*/
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    /**
     * Aggiunge un prodotto al carrello dell'utente autenticato.
     *
     * @param ISBN_prodotto l'ISBN del prodotto da aggiungere al carrello
     * @param request la richiesta HTTP
     * @param response la risposta HTTP
     * @return true se il prodotto viene aggiunto con successo al carrello, false altrimenti
     */
    public boolean aggiungiProdottoAlCarrelloUtente(String ISBN_prodotto,HttpServletRequest request, HttpServletResponse response){

        try {
            ProdottoDAO pd = new ProdottoDAO();
            Prodotto prodotto = pd.cercaPerISBN(ISBN_prodotto);
            if(prodotto!=null){

                if(request.getSession(false).getAttribute("carrello")==null){

                    request.getSession(false).setAttribute("carrello",new Carrello());
                }
                Carrello cart= (Carrello) request.getSession().getAttribute("carrello");
                if(!cart.isPresent(prodotto)){

                    cart.aggiungiProdotto(prodotto,1);
                    request.getSession(false).setAttribute("carrello",cart);
                    request.getSession(false).setAttribute("totale",Math.round(cart.prezzoTotale()*100.0)/100.0);
                    request.getSession(false).setAttribute("quantity",prodotto.getQuantita());

                    //response.sendRedirect("/WEB-INF/Interface/index.jsp");

                }
                else{

                }
                dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/homeUtente.jsp");
                dispatcher.forward(request,response);


            }

            else{
                /*pagina di errore*/
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    /**
     * Rimuove un prodotto dal carrello dell'utente.
     *
     * @param isbn l'ISBN del prodotto da rimuovere dal carrello
     * @param request la richiesta HTTP
     * @param response la risposta HTTP
     */
    public void rimuoviProdottoDalCarrello(String isbn, HttpServletRequest request, HttpServletResponse response) {

        String idProdotto2 = request.getParameter("isbn");
        try {
            ProdottoDAO pd = new ProdottoDAO();
            Prodotto product = pd.cercaPerISBN(idProdotto2);


            Carrello cart1 = (Carrello) request.getSession().getAttribute("carrello");
            cart1.rimuoviProdotto(product);
            System.out.println("messi");
            request.getSession(false).setAttribute("totale", Math.round(cart1.prezzoTotale() * 100.0) / 100.0);
            request.getSession(false).setAttribute("carrello", cart1);
            request.getSession(false).setAttribute("quantity", cart1.getCartItems().size());
            dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException | ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Rimuove un prodotto dal carrello dell'utente autenticato.
     *
     * @param isbn l'ISBN del prodotto da rimuovere dal carrello
     * @param request la richiesta HTTP
     * @param response la risposta HTTP
     */
    public void rimuoviProdottoDalCarrelloUtente(String isbn, HttpServletRequest request, HttpServletResponse response) {

        String idProdotto2 = request.getParameter("isbn");
        try {
            ProdottoDAO pd = new ProdottoDAO();
            Prodotto product = pd.cercaPerISBN(idProdotto2);


            Carrello cart1 = (Carrello) request.getSession().getAttribute("carrello");
            cart1.rimuoviProdotto(product);
            System.out.println("messi");
            request.getSession(false).setAttribute("totale", Math.round(cart1.prezzoTotale() * 100.0) / 100.0);
            request.getSession(false).setAttribute("carrello", cart1);
            request.getSession(false).setAttribute("quantity", cart1.getCartItems().size());
            dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/homeUtente.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException | IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Crea un nuovo ordine utilizzando i prodotti presenti nel carrello.
     *
     * @param request la richiesta HTTP
     * @param response la risposta HTTP
     * @throws ServletException se si verifica un errore durante la gestione della servlet
     * @throws IOException se si verifica un errore di I/O durante la gestione della servlet
     */
    public void creaOrdine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Carrello cart= (Carrello) request.getSession(false).getAttribute("carrello");

        if(cart==null){
            boolean carrellovuoto=true;
            request.setAttribute("carrelloVuoto",carrellovuoto);

            dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/carrello.jsp");
            dispatcher.forward(request,response);

        }else {
            Ordine o = new Ordine();
            ArrayList<ProdottoCarrello> prodotti = cart.getCartItems();
            o.setProdotti(prodotti);
            Utente account1 = (Utente) request.getSession(false).getAttribute("account");
            o.setTotale((float) cart.prezzoTotale());
            o.setEmailUtente(account1.getMail());


            try {
                OrdineDAO od = new OrdineDAO();
                od.createOrder(o);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            cart.getCartItems().clear();

            request.getSession(false).setAttribute("carrello", cart);
            request.getSession(false).setAttribute("totale", Math.round(cart.prezzoTotale()*100.0)/100.0);
            request.getSession(false).setAttribute("quantity", cart.getCartItems().size());

            boolean successo = true;
            request.setAttribute("successo", successo);

            dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
