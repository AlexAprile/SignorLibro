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
    public void qualcosa() throws NoSuchAlgorithmException {
        RequestValidator validator;
        validator = AccountValidator.validateUpForm(request,"esempio@signorlibro.it",
                "Ciaolibro1");
        List<String> lista = validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(true,validator.getErrors().isEmpty());
    }
}
