package Storage.Entity;

import java.util.Date;
import java.util.Objects;

public class Utente {
    private String mail;
    private String password;
    private String nome;
    private String cognome;
    private Date nascita;
    private boolean admin;

    public Utente() {
    }

    public Utente(String mail, String password, String nome, String cognome, Date nascita, boolean admin) {
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

    public Date getNascita() {
        return nascita;
    }

    public void setNascita(Date nascita) {
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
        return admin == utente.admin
                && Objects.equals(mail, utente.mail)
                && Objects.equals(password, utente.password)
                && Objects.equals(nome, utente.nome)
                && Objects.equals(cognome, utente.cognome)
                && Objects.equals(nascita, utente.nascita);
    }

}
