package http;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RequestValidator {
    private final List<String> errors;
    private final HttpServletRequest request;
    private static final Pattern INT_PATTERN=Pattern.compile("^\\d+$");
    private static final Pattern DOUBLE_PATTERN=Pattern.compile("^(-)?(0|[1-9]\\d+\\.\\d+$)");

    public RequestValidator(){
        errors=null;
        request=null;
    }

    public RequestValidator(HttpServletRequest req) {
        this.errors = new ArrayList<>();
        this.request=req;
    }

    public void addError( String msg){ errors.add(msg);}

    public boolean hashErrors(){return !errors.isEmpty();}

    public List<String> getErrors() {
        return errors;
    }

    public boolean gatherError(boolean condition, String message){
        if(condition){
            return true;
        }else {
            errors.add(message);
            return false;
        }
    }
    private boolean required(String value){return value!=null &&!value.isBlank();}
    public boolean assertMatch(String value,Pattern regexp,String msg){
        //String param=request.getParameter(value);
        boolean condition=required(value)&&regexp.matcher(value).matches();
        return gatherError(condition,msg);
    }

    public boolean assertInt(String value,String msg){ return assertMatch(value,INT_PATTERN,msg);}
    public boolean assertDouble(String value, String msg){ return assertMatch(value,DOUBLE_PATTERN,msg);}

    public boolean assertEmail(String value,String msg){
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return assertMatch(value,pattern,msg);
    }

    public static boolean assertDate(String value, String msg) {
        // Il pattern seguente corrisponde a una data nel formato "YYYY-MM-DD"
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

        // Verifica che la data corrisponda al pattern
        if (!pattern.matcher(value).matches()) {
            return false;
        }

        // Estrai l'anno, il mese e il giorno dalla stringa della data
        String[] parts = value.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        try {
            // Crea un oggetto LocalDate per la data specificata
            LocalDate date = LocalDate.of(year, month, day);

            // Ottieni la data corrente
            LocalDate today = LocalDate.now();

            // Verifica se la data è precedente o uguale al giorno corrente
            return !date.isAfter(today);
        } catch (Exception e) {
            // Eccezione nella conversione della data
            return false;
        }
    }

}
