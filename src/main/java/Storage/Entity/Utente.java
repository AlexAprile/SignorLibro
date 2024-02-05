package Storage.Entity;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Utente {

    private String mail;
    private String password;
    private String nome;
    private String cognome;
    private GregorianCalendar nascita;
    private boolean admin;

    public Utente(String mail, String password, String nome, String cognome, GregorianCalendar nascita, boolean admin) {
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.admin = admin;
        this.nascita = nascita;
    }

    //Per amministratore
    public Utente(String mail, String password, boolean admin) {
        this.mail = mail;
        this.password = password;
        this.admin = admin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public GregorianCalendar getNascita() {
        return nascita;
    }

    public void setNascita(GregorianCalendar nascita) {
        this.nascita = nascita;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return admin == utente.admin && Objects.equals(mail, utente.mail) && Objects.equals(password, utente.password) && Objects.equals(nome, utente.nome) && Objects.equals(cognome, utente.cognome) && Objects.equals(nascita, utente.nascita);
    }

}
