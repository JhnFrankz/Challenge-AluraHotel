package dao;

import model.Huesped;
import model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public int guardar(Reserva reserva) {
        try {
            String query = "INSERT INTO reservas " +
                    "(fecha_entrada, fecha_salida, valor, forma_pago) " +
                    "VALUES (?, ?, ?, ?);";

            try (PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setDate(1, reserva.getFechaEntrada());
                statement.setDate(2, reserva.getFechaSalida());
                statement.setDouble(3, reserva.getValor());
                statement.setString(4, reserva.getFormaPago());

                statement.execute();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        reserva.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reserva.getId();
    }

    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();

        try {
            String query = "SELECT * FROM reservas";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Reserva reserva = new Reserva(
                                resultSet.getInt("id"),
                                resultSet.getDate("fecha_entrada"),
                                resultSet.getDate("fecha_salida"),
                                resultSet.getDouble("valor"),
                                resultSet.getString("forma_pago")
                        );
                        reservas.add(reserva);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reservas;
    }

    public void eliminar(Reserva reserva) {
        // TODO
    }

    public void actualizar(Reserva reserva) {
        // TODO
    }

    public Reserva buscar(int id) {
        // TODO
        return null;
    }
}
