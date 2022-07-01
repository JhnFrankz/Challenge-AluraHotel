package dao;

import model.Huesped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Huesped> listar() {
        List<Huesped> huespedes = new ArrayList<>();

        try {
            String query = "SELECT * FROM huespedes";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Huesped huesped = new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("reserva_id")
                        );
                        huespedes.add(huesped);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return huespedes;
    }

    public int eliminar(int id) {
        try {
            String query = "DELETE FROM huespedes WHERE id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Huesped buscar(int id) {
        return null;
    }

    public int modificar(Huesped huesped) {
        try {
            String query = "UPDATE huespedes SET " +
                    "nombre = ?, " +
                    "apellido = ?, " +
                    "fecha_nacimiento = ?, " +
                    "nacionalidad = ?, " +
                    "telefono = ?, " +
                    "reserva_id = ? " +
                    "WHERE id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFechaNacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getReservaId());
                statement.setInt(7, huesped.getId());

                statement.execute();

                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
