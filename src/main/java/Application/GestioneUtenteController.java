package Application;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import Storage.GestioneAcquistiService;
import Storage.GestioneUtenteService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GestioneUtenteController", value = "/GestioneUtenteServlet/*")
public class GestioneUtenteController {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        String utente;
        GestioneUtenteService gestioneUtenteService = new GestioneUtenteService();


        switch (path) {

            case "/UpdateData":
                utente = request.getParameter("email");
                gestioneUtenteService.modificaDatiAccount(utente);
                dispatcher= request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request,response);
                break;

            case "/UpdatePassword":

                gestioneUtenteService.modificaPasswordAccount(utente);
                break;

            case "/showAllUtent":

                gestioneUtenteService.getAllAccount(utente);
                break;


        }


    }
}
