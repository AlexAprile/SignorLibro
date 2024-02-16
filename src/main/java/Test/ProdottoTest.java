package Test;

import http.ProductValidator;
import http.RequestValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String dateString = "01/01/2010";
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RequestValidator validator;
        validator=ProductValidator.validateUpForm(request,"il",20,"libro per bambini",date,"9788854172388");
        List<String> lista=validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(false,validator.getErrors().isEmpty());
    }

    @Test
    public void TC_5_4(){
        String dateString = "01/01/2030";
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RequestValidator validator;
        validator=ProductValidator.validateUpForm(request,"il piccolo principe",20,"libro per bambini",date,"9788854172388");
        List<String> lista=validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(false,validator.getErrors().isEmpty());
    }

    @Test
    public void TC_5_5(){
        String dateString = "01/01/2010";
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RequestValidator validator;
        validator=ProductValidator.validateUpForm(request,"il piccolo principe",20,"libro per bambini",date,"978885417238800");
        List<String> lista=validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(false,validator.getErrors().isEmpty());
    }

    @Test
    public void TC_5_6(){
        String dateString = "01/01/2010";
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        RequestValidator validator;
        validator=ProductValidator.validateUpForm(request,"il piccolo principe",20,"libro per bambini",date,"9788854172388");
        List<String> lista=validator.getErrors();
        for (String temp:lista)
            System.out.println(temp);
        Assert.assertEquals(true,validator.getErrors().isEmpty());
    }
}
