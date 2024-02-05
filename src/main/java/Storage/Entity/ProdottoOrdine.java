package Storage.Entity;

public class ProdottoOrdine extends Prodotto{
    private int quantitaOrdine;
    private int idOrdine;
    public ProdottoOrdine(String titolo, String autore, String isbn, double prezzo, String copertina, String categoria, int quantita) {
        super(titolo, autore, isbn, prezzo, copertina, categoria, quantita);
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public void setQuantitaOrdine(int quantitaOrdine) {
        this.quantitaOrdine = quantitaOrdine;
    }

    public int getQuantitaOrdine() {
        return quantitaOrdine;
    }

    public int getIdOrdine() {
        return idOrdine;
    }
}
