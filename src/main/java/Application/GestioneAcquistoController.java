package Application;

import Storage.GestioneAcquistiService;
import Storage.GestioneProdottoService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "GestioneAcquistoController", value = "/GestioneAcquistoController/*")
public class GestioneAcquistoController extends HttpServlet {
    private RequestDispatcher dispatcher;
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

            case"/addCart":
                isbn = request.getParameter("isbn");
                gas.aggiungiProdottoAlCarrello(isbn,request,response);
                //dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
                //dispatcher.forward(request,response);
                break;

            case "/rimuoviCarrello":
                isbn = request.getParameter("isbn");
                gas.rimuoviProdottoDalCarrello(isbn,request,response);
                dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/index.jsp");
                dispatcher.forward(request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
