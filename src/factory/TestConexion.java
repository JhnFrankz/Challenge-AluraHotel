package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().recuperaConexion();

        System.out.println("Cerrando conexion");
        connection.close();

    }
}
