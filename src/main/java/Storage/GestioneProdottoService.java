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
