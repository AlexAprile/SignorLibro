import Application.AutenticazioneController;

        import Storage.RegistrazioneService;
        import http.AccountValidator;
        import http.AccountValidatorUpdate;
        import http.InvalidRequestException;
        import http.RequestValidator;
        import jakarta.servlet.*;
        import jakarta.servlet.http.*;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.UnsupportedEncodingException;
        import java.security.NoSuchAlgorithmException;
        import java.security.Principal;
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
    public void TestRegistrazione2_1(){
        try {
            RequestValidator validator=AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8studenti.unisa.it",
                    "Alessandro","Aprile","Aprile08");
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(false,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestRegistrazione2_5(){
        try {
            RequestValidator validator=AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8@studenti.unisa.it",
                    "Alessandro1","Aprile","Aprile08");
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(false,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestRegistrazione2_6(){
        try {
            RequestValidator validator=AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8@studenti.unisa.it",
                    "Alessandro1","Aprile","Aprile08");
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(false,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
