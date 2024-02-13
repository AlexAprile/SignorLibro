package Storage;

import Storage.DAO.CarrelloDAO;
import Storage.DAO.ProdottoDAO;
import Storage.Entity.Carrello;
import Storage.Entity.Prodotto;
import Storage.Entity.Utente;
import http.ErrorHandler;
import http.InvalidRequestException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class GestioneAcquistiService {
    public boolean aggiungiProdottoAlCarrello(String ISBN_prodotto, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ProdottoDAO pd = new ProdottoDAO();
            Prodotto prodotto = pd.cercaPerISBN(ISBN_prodotto);

            if (request.getSession(false).getAttribute("Utente") == null)
                if (prodotto != null) {
                    if (request.getSession(false).getAttribute("carrello") == null) {

                        request.getSession(false).setAttribute("carrello", new Carrello());
                    }

                    Carrello cart = (Carrello) request.getSession().getAttribute("carrello");


                    if (!cart.isPresent(prodotto)) {

                        cart.aggiungiProdotto(prodotto, 1);
                        request.getSession(false).setAttribute("carrello", cart);
                        request.getSession(false).setAttribute("totale", Math.round(cart.prezzoTotale() * 100.0) / 100.0);
                        request.getSession(false).setAttribute("quantity", cart.getCartItems().size());

                        response.sendRedirect("/WEB-INF/index.jsp");
                    } else {
                        request.getSession(false).setAttribute("prodotto2", prodotto);
                        response.sendRedirect("/WEB-INF/index.jsp" + prodotto.getId());
                    }


                } else {
                    /*pagina di errore*/
                }
            else {
                ErrorHandler handler=new ErrorHandler();
                handler.authenicate(request.getSession(false));
                CarrelloDAO carrelloDAO=new CarrelloDAO();
                Utente utente= (Utente) request.getSession().getAttribute("utente");
                //carrelloDAO.addItem(prodotto, utente.getMail());
            }


            } catch(SQLException throwables){
                throwables.printStackTrace();
            } catch(IOException e){
                throw new RuntimeException(e);
            } catch (InvalidRequestException e) {
                e.handle(request,response);
        }
        return false;
    }
}
