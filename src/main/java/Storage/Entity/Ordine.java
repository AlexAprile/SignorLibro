package Storage.Entity;

import java.util.ArrayList;

public class Ordine {
    String emailUtente;
    float totale;
    ArrayList<ProdottoOrdine> prodotti;

    public Ordine(String emailUtente, float totale, ArrayList<ProdottoOrdine> prodotti) {
        this.emailUtente = emailUtente;
        this.totale = totale;
        this.prodotti = prodotti;
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

    public ArrayList<ProdottoOrdine> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<ProdottoOrdine> prodotti) {
        this.prodotti = prodotti;
    }
}
