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
        String id;
        GestioneAcquistiService gas = new GestioneAcquistiService();

        switch (path){
            case"/showCartGuest":
                dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/carrello.jsp");
                dispatcher.forward(request,response);
                break;

            case"/addCart":
                id = request.getParameter("idISBN");
                gas.aggiungiProdottoAlCarrello(id,request,response);
                dispatcher= request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);
                break;

            case "/rimuoviCarrello":
                id = request.getParameter("idISBN");
                gas.rimuoviProdottoDalCarrello(id,request,response);
                dispatcher= request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
