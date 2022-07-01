package controller;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import model.Huesped;

import java.sql.Connection;
import java.util.List;

public class HuespedesController {

    private HuespedDAO huespedDAO;

    public HuespedesController() {
        Connection connection = new ConnectionFactory().recuperaConexion();
        this.huespedDAO = new HuespedDAO(connection);
    }

    public void guardar(Huesped huesped) {
        this.huespedDAO.guardar(huesped);
    }

    public List<Huesped> listar() {
        return this.huespedDAO.listar();
    }

    public int modificar(Huesped huesped) {
        return this.huespedDAO.modificar(huesped);
    }

    public int eliminar(int id) {
        return this.huespedDAO.eliminar(id);
    }
}
