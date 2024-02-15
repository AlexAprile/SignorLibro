import http.ProductValidator;
import http.RequestValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

public class ProdottoTest {
    HttpServletResponse response;
    HttpServletRequest request;
    @Before
    public void setUp(){
        request=mock(HttpServletRequest.class);
        response=mock(HttpServletResponse.class);
    }

    @Test
    public void TC_5_1(){
        RequestValidator validator;
        validator=ProductValidator.validateUpForm(request,"il",20,"libro per bambini",8,"9788854172388");
        List<String> lista=validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(true,validator.getErrors().isEmpty());
    }
}
