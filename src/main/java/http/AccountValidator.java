package http;

import jakarta.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class AccountValidator{
    public static RequestValidator validateUpForm(HttpServletRequest req,String email,String password)
            throws NoSuchAlgorithmException {
        RequestValidator validator=new RequestValidator(req);
        validator.assertEmail(email,"Email non valida es. example123@example.com");
        validator.assertMatch(password,Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"),
                "Password non valida es. Abc12345");
        return validator;
    }
}
