package http;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

public abstract class Controller extends HttpServlet  {
    @Resource(name = "jdbc/SignorLibro")
    protected static DataSource source;
    public static void validate(RequestValidator validator) throws InvalidRequestException {
        if(validator.hashErrors()) {
                throw new InvalidRequestException("Validation Error", validator.getErrors(), HttpServletResponse.SC_BAD_REQUEST);
            }
    }
}
