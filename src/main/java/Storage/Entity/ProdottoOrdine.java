package Storage.Entity;

public class ProdottoOrdine extends Prodotto{
    /**
     * quantitaOrdine deve essere >0
     */
    private int quantitaOrdine;
    private int idOrdine;
    public ProdottoOrdine(String titolo, String autore, String isbn, double prezzo, String copertina, String categoria, int quantita) {
        super(titolo, autore, isbn, prezzo, copertina, categoria, quantita);
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public void setQuantitaOrdine(int quantitaOrdine) {
        if(quantitaOrdine>0)
            this.quantitaOrdine = quantitaOrdine;
        else throw new RuntimeException("la quantita deve essere > 0");
    }

    public int getQuantitaOrdine() {
        return quantitaOrdine;
    }

    public int getIdOrdine() {
        return idOrdine;
    }
}
