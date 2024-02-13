package Storage;

import Storage.DAO.ProdottoDAO;
import Storage.Entity.Prodotto;
import http.ErrorHandler;
import http.InvalidRequestException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneProdottoService {
    public Prodotto getProdotto(String isbn) throws SQLException {
        return new ProdottoDAO().cercaPerISBN(isbn);
    }
    public ArrayList<Prodotto> getAllProdotto() throws SQLException {
        return new ProdottoDAO().cercaTuttiProdotti();
    }
    public boolean aggiungiProdotto(Prodotto prodotto, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        ErrorHandler handler = new ErrorHandler();
        try {
            handler.authorize(request.getSession(false));

            request.setAttribute("back", "home");
            String address = "/WEB-INF/views/crm/createProduct.jsp";
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
}
