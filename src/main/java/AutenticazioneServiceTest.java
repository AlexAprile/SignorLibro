

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import Storage.DAO.UtenteDAO;
import Storage.Entity.Utente;
import Storage.AutenticazioneService;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;

public class AutenticazioneServiceTest {

    @Test
    public void testLogin() throws SQLException, NoSuchAlgorithmException {
        // Creazione di un mock per UtenteDAO
        UtenteDAO daoMock = Mockito.mock(UtenteDAO.class);

        // Creazione di un utente di esempio
        Utente utente = new Utente();
        utente.setMail("test@example.com");
        utente.setPassword("password");

        // Configurazione del comportamento del mock per ritornare l'utente di esempio
        Mockito.when(daoMock.fetchAccountWithPsw(Mockito.eq("test@example.com"), Mockito.anyString())).thenReturn(utente);

        // Creazione di un oggetto AutenticazioneService
        AutenticazioneService service = new AutenticazioneService();

        // Chiamata al metodo login() con email e password
        Utente result = service.login("test@example.com", "password");

        // Verifica che il risultato ritornato sia non nullo
        assertNotNull(result);

        // Verifica che il risultato sia uguale all'utente di esempio
        assertEquals(utente, result);
    }


}

