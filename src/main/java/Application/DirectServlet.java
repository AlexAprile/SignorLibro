package Application;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Storage.Entity.Utente;

import java.io.IOException;

@WebServlet("/direct-servlet")
public class DirectServlet extends HttpServlet {

    public  void init(){}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("registrazione");
        //true = registrazione
        //false = login
        //da profilo.jsp
        String address;

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente == null){
            if(check == null) {
                address = "./WEB-INF/profilo.jsp";
            } else if (check.equals("true")) {
                address = "./WEB-INF/Interface/Registrazione/registrazione.jsp";
            }
            else
                address = "./WEB-INF/Interface/login.jsp";
        }else{
            address = "./WEB-INF/account.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(address);
        rd.forward(request, response);
    }

    public void destroy() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
