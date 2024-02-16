package Storage.Entity;

import Storage.DAO.CarrelloDAO;
import Storage.DAO.ProdottoDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Carrello {
    private ArrayList<ProdottoCarrello> carrello;

    public ArrayList<ProdottoCarrello> getCarrello(){
        return carrello;
    }
    public Carrello(){
        this.carrello = new ArrayList<>();
    }

    public ArrayList<ProdottoCarrello> getCartItems() {
        return carrello;
    }

    public void setCarrello(ArrayList<ProdottoCarrello> cartItems) {
        this.carrello = cartItems;
    }

    public void aggiungiProdotto(Prodotto p, int q) throws SQLException {
        ProdottoCarrello pc = new ProdottoCarrello(p,q);
        carrello.add(pc);
    }

    public void rimuoviProdotto(Prodotto p) {

        for (int i = 0; i < carrello.size(); i++) {
            System.out.println(carrello.get(i).getProdotto().getTitolo());
            if (p.getIsbn().equals(carrello.get(i).getProdotto().getIsbn())) {
                if (carrello.get(i).getQuantita() == 1) {
                    carrello.remove(i);
                } else {
                    carrello.get(i).setQuantita(carrello.get(i).getQuantita()-1);
                }

            }
        }
    }

    public boolean isPresent(Prodotto p){

        for(int i=0; i<carrello.size(); i++){

            if( p.equals(carrello.get(i).getProdotto())){

                return true;
            }
        }
        return  false;
    }



    public double prezzoTotale(){

        double total=0;

        for (ProdottoCarrello pc: carrello) {
            //somma il prodotto presente nel carrello per
            //il suo prezzo cadauno e la sua quantità
            total+=(pc.getProdotto().getPrezzo()*pc.getQuantita());
        }
        return total;
    }


}
