package http;

import jakarta.servlet.http.HttpServletRequest;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class AccountValidatorUpdate {
    public static RequestValidator validateUpForm(HttpServletRequest req,String email,String nome,String cognome,String password) throws NoSuchAlgorithmException {
        RequestValidator validator=new RequestValidator(req);
        validator.assertEmail(email,"Email non valida es. example123@example.com");
        validator.assertMatch(password,Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"),"la Password non è valida");
        validator.assertMatch(nome,Pattern.compile("^[A-Za-z ]{3,30}$"),"il nome deve contenere almeno 3 caratteri e al massimo 30");
        validator.assertMatch(cognome,Pattern.compile("^[A-Za-z ]{3,40}$"),"il cognome deve contere almeno 3 caratteri e al massimo 30");
        return validator;
    }
}
