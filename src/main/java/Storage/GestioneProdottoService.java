package Storage;

import Storage.DAO.ProdottoDAO;
import Storage.Entity.Prodotto;
import com.mysql.cj.Session;
import http.ErrorHandler;
import http.InvalidRequestException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Classe che gestisce le operazioni di gestione dei prodotti.
 */
public class GestioneProdottoService {
    /**
     * Recupera un prodotto dal database utilizzando l'ISBN.
     *
     * @param isbn l'ISBN del prodotto da recuperare
     * @return l'oggetto Prodotto corrispondente all'ISBN specificato
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     */
    public Prodotto getProdotto(String isbn) throws SQLException {
        return new ProdottoDAO().cercaPerISBN(isbn);
    }
    /**
     * Recupera tutti i prodotti dal database.
     *
     * @return un ArrayList contenente tutti i prodotti presenti nel database
     * @throws SQLException se si verifica un errore durante l'interazione con il database
     */
    public ArrayList<Prodotto> getAllProdotto() throws SQLException {
        return new ProdottoDAO().cercaTuttiProdotti();
    }
    /**
     * Aggiunge un nuovo prodotto.
     *
     * @param prodotto l'oggetto Prodotto da aggiungere
     * @param response la risposta HTTP
     * @param request la richiesta HTTP
     * @return true se il prodotto viene aggiunto con successo, false altrimenti
     * @throws ServletException se si verifica un errore durante la gestione della servlet
     * @throws IOException se si verifica un errore di I/O durante la gestione della servlet
     */
    public boolean aggiungiProdotto(Prodotto prodotto, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        ErrorHandler handler = new ErrorHandler();
        try {
            handler.authorize(request.getSession(false));

            request.setAttribute("back", "home");
            String address = "/WEB-INF/Interface/createProduct.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        } catch (InvalidRequestException e) {
            e.handle(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    /**
     * Modifica un prodotto esistente.
     *
     * @param prodotto l'oggetto Prodotto da modificare
     * @param session l'oggetto HttpSession
     * @return true se la modifica del prodotto ha avuto successo, false altrimenti
     * @throws InvalidRequestException se si verifica un'eccezione di tipo InvalidRequestException
     */
    public boolean modificaProdotto(Prodotto prodotto, HttpSession session) throws InvalidRequestException {
        ProdottoDAO prodottoDAO=new ProdottoDAO();
        ErrorHandler handler=new ErrorHandler();
        try {
            handler.authorize(session);
            prodottoDAO.updateProduct(prodotto);
            return true;
        } catch (InvalidRequestException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Rimuove un prodotto esistente dal database.
     *
     * @param prodotto l'oggetto Prodotto da rimuovere
     * @param session l'oggetto HttpSession
     * @return true se il prodotto viene rimosso con successo, false altrimenti
     * @throws InvalidRequestException se si verifica un'eccezione di tipo InvalidRequestException
     */
    public boolean rimuoviProdotto(Prodotto prodotto,HttpSession session){
        ErrorHandler handler=new ErrorHandler();
        try {
            handler.authorize(session);
            ProdottoDAO prodottoDAO=new ProdottoDAO();
            prodottoDAO.eliminaProdotto(prodotto.getId());
            return true;
        } catch (InvalidRequestException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
