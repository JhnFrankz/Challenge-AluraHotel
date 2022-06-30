package controller;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import model.Reserva;

import java.sql.Connection;
import java.util.List;

public class ReservasController {

    private ReservaDAO reservaDAO;

    public ReservasController() {
        Connection connection = new ConnectionFactory().recuperaConexion();
        this.reservaDAO = new ReservaDAO(connection);
    }

    public int guardar(Reserva reserva) {
        return this.reservaDAO.guardar(reserva);
    }

    public List<Reserva> listar() {
        return this.reservaDAO.listar();
    }

}
