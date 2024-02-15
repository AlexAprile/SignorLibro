package http;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.Calendar;


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

    public boolean assertDate(Date value, String msg) {
        // Convertire la data di nascita in una stringa
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataNascitaString = dateFormat.format(value);

        // Definire la regex per verificare l'età di almeno 18 anni
        //String regex = "^(?:0[1-9]|1\\d|2[0-8])/(?:0[1-9]|1[0-2])/((?:19|20)\\d{2})$";
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);

        // Controllare se la stringa corrisponde alla regex e se l'età è di almeno 18 anni
        if (assertMatch(dataNascitaString, pattern, msg)) {
            LocalDate datalocalCorrente = LocalDate.now();
            Date dataCorrente= Date.from(datalocalCorrente.atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (value.before(dataCorrente) || value.equals(dataCorrente)) {
                return true; //va bene
            } else {
                errors.add("Data superiore alla data corrente");
                return false;
            }
        } else {
            // La stringa non corrisponde alla regex
            return false;
        }

    }


    public boolean assertDateMaggiorenne(Date value, String msg){
        // Convertire la data di nascita in una stringa
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataNascitaString = dateFormat.format(value);

        // Definire la regex per verificare l'età di almeno 18 anni
        //String regex = "^(?:0[1-9]|1\\d|2[0-8])/(?:0[1-9]|1[0-2])/((?:19|20)\\d{2})$";
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);

        // Controllare se la stringa corrisponde alla regex e se l'età è di almeno 18 anni
        if (assertMatch(dataNascitaString, pattern, msg)) {

            LocalDate dataCorrente = LocalDate.now();
            LocalDate data18AnniFa = dataCorrente.minusYears(18);

            Date data18anni= Date.from(data18AnniFa.atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (value.before(data18anni) || value.equals(data18anni)) {
                return true; //va bene
            } else {
                errors.add("Utente non maggiorenne");
                return false; //non maggiorenne
            }
        } else {
            // La stringa non corrisponde alla regex
            return false;
        }

    }

}
