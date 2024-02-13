package Storage;

import Storage.DAO.ProdottoDAO;
import Storage.Entity.Carrello;
import Storage.Entity.Prodotto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class GestioneAcquistiService {
    public boolean aggiungiProdottoAlCarrello(String ISBN_prodotto, HttpServletRequest request, HttpServletResponse response) {

        try {
            ProdottoDAO pd = new ProdottoDAO();
            Prodotto prodotto = pd.cercaPerISBN(ISBN_prodotto);

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


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void rimuoviProdottoDalCarrello(String isbn, HttpServletRequest request, HttpServletResponse response) {

        String idProdotto2 = request.getParameter("id");
        try {
            ProdottoDAO pd = new ProdottoDAO();
            Prodotto product = pd.cercaPerISBN(idProdotto2);


            Carrello cart1 = (Carrello) request.getSession().getAttribute("carrello");
            cart1.rimuoviProdotto(product);
            request.getSession(false).setAttribute("totale", Math.round(cart1.prezzoTotale() * 100.0) / 100.0);
            request.getSession(false).setAttribute("carrello", cart1);
            request.getSession(false).setAttribute("quantity", cart1.getCartItems().size());
            response.sendRedirect("/GameOver_war_exploded/CartServlet/showCart");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
