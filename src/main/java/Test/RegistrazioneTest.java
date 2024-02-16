package Test;

import http.AccountValidatorUpdate;
import http.RequestValidator;
import jakarta.servlet.http.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static org.mockito.Mockito.mock;

public class RegistrazioneTest {

    HttpServletRequest request;
    HttpServletResponse response;

    @Before
    public void setUp(){
        request=mock(HttpServletRequest.class);
        response=mock(HttpServletResponse.class);
    }

    @Test
    public void TC_2_1(){
        Date nascita = new Date("23/03/1999");
        try {
            RequestValidator validator=AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8studenti.unisa.it",
                    "Alessandro","Aprile","Aprile08", nascita);
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(true,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_2_5(){
        Date nascita = new Date("23/03/1999");
        try {
            RequestValidator validator=AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8@studenti.unisa.it",
                    "Alessandro1","Aprile","Aprile08", nascita);
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(true,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_2_6(){
        Date nascita = new Date("23/03/1999");
        try {
            RequestValidator validator=AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8@studenti.unisa.it",
                    "Alessandro1","Aprile","Aprile08", nascita);
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(true,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_2_7(){
        Date nascita = new Date("23/03/2020");
        try {
            RequestValidator validator = AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8@studenti.unisa.it",
                    "Alessandro","Aprile","Aprile08", nascita);
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(true,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_2_8(){
        Date nascita = new Date("23/03/1999");
        try {
            RequestValidator validator = AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8@studenti.unisa.it",
                    "Alessandro","Aprile","Aprile08", nascita);
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(false,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
