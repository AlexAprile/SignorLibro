package http;

import jakarta.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

public class ProductValidator{
    public static RequestValidator validateUpForm(HttpServletRequest req,String titolo,double prezzo,String descrizione,int quantita,String isbn){
        RequestValidator validator=new RequestValidator(req);
        validator.assertMatch(titolo,Pattern.compile("^.{3,50}$"),"il titolo deve contenere tra i 3 e i 50 caratteri");
       // validator.assertDouble(prezzo,"il prezzo deve essere un numero con la virgola");
        validator.assertMatch(descrizione,Pattern.compile("^.{0,5000}$"),"la descrizione deve contenere tra i 0 e i 5000 caratteri");
        //validator.assertInt(quantita,"la quantita deve essere un numero intero");
        validator.assertMatch(isbn,Pattern.compile("^(978|979)\\d{10}$"),"Isbn non rispetta il formato");

        return validator;
    }
}
