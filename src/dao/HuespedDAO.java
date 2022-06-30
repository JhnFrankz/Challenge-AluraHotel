package dao;

import model.Huesped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HuespedDAO {

    private Connection connection;

    public HuespedDAO(Connection connection) {
        this.connection = connection;
    }

    public void guardar(Huesped huesped) {
        try {
            String query = "INSERT INTO huespedes " +
                    "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reserva_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                System.out.println(huesped.getNombre());
                System.out.println(huesped.getApellido());
                System.out.println(huesped.getFechaNacimiento());
                System.out.println(huesped.getNacionalidad());
                System.out.println(huesped.getTelefono());
                System.out.println(huesped.getReservaId());

                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                //Tiene que ser la reserva generada por el DAO de reserva
                statement.setInt(6, huesped.getReservaId());

                statement.execute();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        huesped.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminar(Huesped huesped) {
        // TODO
    }

    public void actualizar(Huesped huesped) {
        // TODO
    }

    public Huesped buscar(int id) {
        // TODO
        return null;
    }
}
