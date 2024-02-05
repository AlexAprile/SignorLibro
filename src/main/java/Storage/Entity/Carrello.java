package Storage.Entity;

import java.util.ArrayList;

public class Carrello {

    private ArrayList<ProdottoCarrello> carrello;


    public Carrello(){
        this.carrello = new ArrayList<>();
    }

    public ArrayList<ProdottoCarrello> getCartItems() {
        return carrello;
    }

    public void setCarrello(ArrayList<ProdottoCarrello> cartItems) {
        this.carrello = cartItems;
    }

    public void aggiungiProdotto(Prodotto p, int q){
        carrello.add(new ProdottoCarrello(p,q));
    }

    public void rimuoviProdotto(Prodotto p){

        for (int i=0; i<carrello.size(); i++){

            if(p.equals(carrello.get(i).getProdotto())){
                if (carrello.get(i).getQuantita() == 1)
                    carrello.remove(i);
                else
                    carrello.get(i).setQuantita(carrello.get(i).getQuantita()-1);
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

            total+=pc.getProdotto().getPrezzo();
        }
        return total;
    }


}
