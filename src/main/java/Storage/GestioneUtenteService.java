package Storage;

import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneUtenteService {
    public boolean modificaDatiUtente(Utente utente){
        UtenteDAO utenteDAO=new UtenteDAO();
        try {
            utenteDAO.updateUtente(utente);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    public ArrayList<Utente> getAllUtenti(){
        UtenteDAO utenteDAO=new UtenteDAO();
        ArrayList<Utente> users= null;
        try {
            users = UtenteDAO.fetchUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

}
