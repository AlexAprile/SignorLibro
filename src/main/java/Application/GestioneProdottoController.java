package Application;

import Storage.DAO.ProdottoDAO;
import Storage.Entity.Prodotto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(name = "GestioneProdottoController", value = "/GestioneProdottoController/*")

public class GestioneProdottoController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();

        /** carico i prodotti nella vetrina nella memoria globlale **/

        try {
            ArrayList<Prodotto> prodotti;
            ProdottoDAO sqlProductDao= new ProdottoDAO();
            prodotti=sqlProductDao.cercaTuttiProdotti();
            getServletContext().setAttribute("vetrina",prodotti);
            System.out.println("ciao");
            System.out.println("tutto sotto controllo per ora");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=(req.getPathInfo()!=null) ? req.getPathInfo():"/";
        RequestDispatcher dispatcher;


        ProdottoDAO sqlProductDao=new ProdottoDAO();
        Prodotto product=new Prodotto();
        ArrayList<Prodotto> products=new ArrayList<>();


        switch (path){
            case "/home":
                dispatcher= req.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
                dispatcher.forward(req,resp);
                break;
            case "/showProduct":
                try {
                    product=sqlProductDao.cercaPerISBN(req.getParameter("isbn"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                req.setAttribute("prodotto",product);
                dispatcher=req.getRequestDispatcher("/WEB-INF/Interface/prodottoguest.jsp");
                dispatcher.forward(req,resp);

                break;
        }
    }
   }
