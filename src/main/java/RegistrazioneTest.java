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
    public void TestRegistrazione2_1(){
        try {
            RequestValidator validator=AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8studenti.unisa.it",
                    "Alessandro","Aprile","Aprile08");
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(true,validator.hashErrors());
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
            Assert.assertEquals(true,validator.hashErrors());
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
            Assert.assertEquals(true,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TestRegistrazione2_8(){
        try {
            RequestValidator validator = AccountValidatorUpdate.validateUpForm(request,
                    "a.aprile8@studenti.unisa.it",
                    "Alessandro","Aprile","Aprile08");
            ArrayList<String> lista=(ArrayList<String>) validator.getErrors();
            for (String temp:lista)
                System.out.println(temp);
            Assert.assertEquals(false,validator.hashErrors());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
