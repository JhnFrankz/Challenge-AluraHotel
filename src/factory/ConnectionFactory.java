package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionFactory {

    private final DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/prueba1" +
                "?useTimezone=true&serverTimezone=UTC");
        pooledDataSource.setUser("root");
        pooledDataSource.setPassword("admin");
        pooledDataSource.setMaxPoolSize(15);

        this.dataSource = pooledDataSource;
    }

    public Connection recuperaConexion() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
