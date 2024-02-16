package Application;

import Storage.DAO.ProdottoDAO;
import Storage.Entity.Prodotto;
import Storage.GestioneProdottoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@WebServlet(name = "GestioneProdottoController", value = "/GestioneProdottoController/*")

public class GestioneProdottoController extends HttpServlet {
   @Override
    public void init() throws ServletException {
        super.init();

        /** carico i prodotti nella vetrina nella memoria globlale **/

       ArrayList<Prodotto> prodotti;
       ProdottoDAO sqlProductDao= new ProdottoDAO();
       try {
           prodotti=sqlProductDao.cercaTuttiProdotti();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       getServletContext().setAttribute("vetrina",prodotti);
       System.out.println("ciao");
       System.out.println("tutto sotto controllo per ora");
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
            case "/showProductUtente":
                try {
                    product=sqlProductDao.cercaPerISBN(req.getParameter("isbn"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                req.setAttribute("prodotto",product);
                dispatcher=req.getRequestDispatcher("/WEB-INF/Interface/prodottoutente.jsp");
                dispatcher.forward(req,resp);

                break;
            case "/risultati":
                ProdottoDAO dao=new ProdottoDAO();
                ArrayList<Prodotto> prodotti= (ArrayList<Prodotto>) dao.search(req.getParameter("ricerca"));
                req.setAttribute("vetrina",prodotti);
                dispatcher= req.getRequestDispatcher("/WEB-INF/Interface/prodotti.jsp");
                dispatcher.forward(req,resp);
                break;
            case "/aggiungiProdotto":
                dispatcher= req.getRequestDispatcher("/WEB-INF/Interface/aggiungiProdotto.jsp");
                dispatcher.forward(req,resp);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        RequestDispatcher dispatcher;
        switch (path){
            case "/creaProdotto":

                ProdottoDAO productDao= new ProdottoDAO();
                Prodotto product=new Prodotto();
                System.out.println("forza napoli");
                System.out.println(request.getParameter("quan"));
                System.out.println(request.getParameter("nome"));

                try {

                    product.setTitolo(request.getParameter("nome"));
                    product.setIsbn(request.getParameter("isbn"));
                    product.setAutore(request.getParameter("autore"));
                    product.setCategoria(request.getParameter("categoria"));


                    String descrizione=request.getParameter("description");
                    product.setDescrizione(descrizione);
                    product.setQuantita(5);
                    product.setPrezzo(6);


                    Part filePart = request.getPart("cover");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    product.setCopertina(fileName);

                    System.out.println("break");

                    if(productDao.creaProdotto(product)){


                        InputStream inputStream = filePart.getInputStream();
                        String uploadPath = "C:\\Users\\utente\\Desktop\\SiLi\\src\\main\\webapp\\Cover";
                        File file = new File(uploadPath + fileName);
                        Files.copy(inputStream, file.toPath());


                        boolean success=true;
                        request.setAttribute("success",success);
                        dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/homeAdmin.jsp");
                        dispatcher.forward(request,response);
                    }
                    else{

                        boolean success=false;
                        request.setAttribute("success",success);
                        dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/homeAdmin.jsp");
                        dispatcher.forward(request,response);

                    }

                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                break;
        }
    }
   }
