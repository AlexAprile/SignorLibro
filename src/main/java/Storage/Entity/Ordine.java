package Storage.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Ordine {
    String emailUtente;
    float totale;

    Date dataOrdine;
    ArrayList<ProdottoCarrello> prodotti;

    int id;

    public Ordine(String emailUtente, float totale, ArrayList<ProdottoCarrello> prodotti, java.util.Date dataOrdine) {
        this.emailUtente = emailUtente;
        this.totale = totale;
        this.prodotti = prodotti;
        this.dataOrdine = (Date) dataOrdine;
    }

    public Ordine() {

    }


    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public ArrayList<ProdottoCarrello> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<ProdottoCarrello> prodotti) {
        this.prodotti = prodotti;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }
}
