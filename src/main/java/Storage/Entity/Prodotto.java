package Storage.Entity;

import java.util.Calendar;
import java.util.Date;

public class Prodotto {
    private int id;
    private String titolo;
    private String autore;

    /** Il prezzo deve essere positivo
     * @invariant prezzo >= 0
     */
    private double prezzo;
    private String descrizione;

    private String copertina;
    /**
     * La dataPubblicazione deve essere precedente o uguale
     * a quella corrente
     */
    private Date dataPubblicazione;
    private String categoria;
    private String isbn;
    /**
     * l'attributo "quantita" deve essere >=0
     * se è >0 è disponibile
     * se è =0 non è disponibile
     */
    private int quantita;

    public Prodotto(String titolo, String autore, String isbn, double prezzo, String copertina, String categoria, int quantita) {
        this.titolo = titolo;
        this.autore = autore;
        this.isbn = isbn;
        this.copertina = copertina;
        this.categoria = categoria;
        setPrezzo(prezzo);
        setQuantita(quantita);
    }

    public void setQuantita(int quantita) {
        if (quantita>=0) {
            this.quantita = quantita;
        }else throw new RuntimeException("la quantita deve essere >=0");
    }

    public void setPrezzo(double prezzo) {
        if(prezzo>=0)
            this.prezzo = prezzo;
        else throw new RuntimeException("il prezzo deve essere >=0");
    }

    public void setDataPubblicazione(Date dataPubblicazione) {
        if(dataPubblicazione.after(Calendar.getInstance().getTime()))
            this.dataPubblicazione = dataPubblicazione;
        else throw new RuntimeException("la data di pubblicazione deve essere precedente alla data corrente");
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantita() {
        return quantita;
    }

    public int getId() {
        return id;
    }

    public Date getDataPubblicazione() {
        return dataPubblicazione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getAutore() {
        return autore;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCopertina() {
        return copertina;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }
}
