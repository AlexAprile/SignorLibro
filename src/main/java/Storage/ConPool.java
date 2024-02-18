package Storage;

import jakarta.inject.Singleton;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConPool  {
    private static DataSource dataSource;

    // Inizializzazione del pool di connessioni
    static {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3308/signor_libro?serverTimezone=" + TimeZone.getDefault().getID());
        p.setDriverClassName("com.mysql.cj.jdbc.Driver");
        p.setUsername("root");
        p.setPassword("root765");
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMinIdle(10);
        p.setRemoveAbandonedTimeout(60);
        p.setRemoveAbandoned(true);
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    // Metodo per ottenere una connessione dal pool
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Metodo per chiudere il pool di connessioni
    public static void close() {
        dataSource.close();
    }
}
