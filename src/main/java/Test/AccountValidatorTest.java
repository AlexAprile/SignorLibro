package Test;

import http.AccountValidator;
import http.RequestValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.mockito.Mockito.mock;

public class AccountValidatorTest {
    HttpServletResponse response;
    HttpServletRequest request;
    @Before
    public void setUp(){
        request=mock(HttpServletRequest.class);
        response=mock(HttpServletResponse.class);
    }

    @Test
    public void TC_1_1() throws NoSuchAlgorithmException {
        RequestValidator validator;
        validator = AccountValidator.validateUpForm(request,"l.pauzanostudenti.unisa.it",
                "Pauzano02");
        List<String> lista = validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(false,validator.getErrors().isEmpty());
    }

    @Test
    public void TC_1_2() throws NoSuchAlgorithmException {
        RequestValidator validator;
        validator = AccountValidator.validateUpForm(request,"l.pauzano@studenti.unisa.it",
                "pauzano02");
        List<String> lista = validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(false,validator.getErrors().isEmpty());
    }

    @Test
    public void TC_1_3() throws NoSuchAlgorithmException {
        RequestValidator validator;
        validator = AccountValidator.validateUpForm(request,"l.pauzano@studenti.unisa.it",
                "Pauzano02");
        List<String> lista = validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(true,validator.getErrors().isEmpty());
    }

}
