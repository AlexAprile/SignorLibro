package http;

import components.Alert;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class InvalidRequestException extends Exception{
    private final List<String> errors;
    private final int errorCode;
    public InvalidRequestException(String msg, List<String> errors,int errorCode){
        super(msg);
        this.errors=errors;
        this.errorCode=errorCode;
    }
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (errorCode){
            case HttpServletResponse.SC_UNAUTHORIZED:
                resp.setStatus(errorCode);
                resp.sendRedirect("signin");
            case HttpServletResponse.SC_BAD_REQUEST:
                req.setAttribute("alert",new Alert(errors,"danger"));
                String backPath= (String) req.getAttribute("back");
                resp.setStatus(errorCode);
                req.getRequestDispatcher(backPath).forward(req,resp);
            default:
                resp.sendError(errorCode,errors.get(0));
        }
    }
}
