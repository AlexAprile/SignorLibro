package Storage.Entity;

public class ProdottoCarrello extends Prodotto{
    private Prodotto prodotto;
    private int quantita;

    public ProdottoCarrello(Prodotto p, int q){
        this.quantita = q;
        this.prodotto = p;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
