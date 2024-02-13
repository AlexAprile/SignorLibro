package Application;

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

        switch (path){
            case"/showCartGuest":
                System.out.println("ciao");
                dispatcher= request.getRequestDispatcher("/WEB-INF/Interface/carrello.jsp");
                dispatcher.forward(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
