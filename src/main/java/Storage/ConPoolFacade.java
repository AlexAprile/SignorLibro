package Storage;

import java.sql.Connection;
import java.sql.SQLException;

public class ConPoolFacade {
    // Metodo per ottenere una connessione dal pool
    public static Connection getConnection() throws SQLException {
        return ConPool.getConnection();
    }

    // Metodo per chiudere il pool di connessioni
    public static void close() {
        ConPool.close();
    }
}
