package http;

import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

public class CardValidator {
    public static RequestValidator validateUpForm(HttpServletRequest req){
        RequestValidator validator=new RequestValidator(req);
        validator.assertMatch("card",Pattern.compile("^^(?!0000)[0-9]{4}[-\\s]?[0-9]{4}[-\\s]?[0-9]{4}[-\\s]?[0-9]{4}$"),"il numero carta deve esste diviso in quattro gruppi di quattro cifre, con opzionali trattini o spazi tra i gruppi e le prime quattro cifre non siano 0");
        validator.assertMatch("cvv",Pattern.compile("^[0-9]{3}$"),"il cvv deve avere 3 cifre");
        return validator;
    }
}
