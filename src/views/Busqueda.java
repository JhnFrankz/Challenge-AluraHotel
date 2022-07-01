package views;

import controller.HuespedesController;
import controller.ReservasController;
import model.Huesped;
import model.Reserva;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modeloHuespedes;
    private DefaultTableModel modeloReservas;
    private HuespedesController huespedesController;
    private ReservasController reservasController;

    /**
     * Create the frame.
     */
    public Busqueda() {
        this.huespedesController = new HuespedesController();
        this.reservasController = new ReservasController();

        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 516);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(647, 85, 158, 31);
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JButton btnBuscar = new JButton("");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnBuscar.setBackground(Color.WHITE);
        btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
        btnBuscar.setBounds(815, 75, 54, 41);
        contentPane.add(btnBuscar);

        JButton btnEditar = new JButton("");
        btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
        btnEditar.setBackground(SystemColor.menu);
        btnEditar.setBounds(587, 416, 54, 41);
        contentPane.add(btnEditar);

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificar();
                limpiarTabla();
                listar();
            }
        });

        JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
        lblNewLabel_4.setBounds(155, 42, 258, 42);
        contentPane.add(lblNewLabel_4);

        JButton btnSalir = new JButton("");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }
        });

        btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(Color.WHITE);
        btnSalir.setBounds(815, 416, 54, 41);
        contentPane.add(btnSalir);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBounds(10, 127, 874, 265);
        contentPane.add(panel);

        tbHuespedes = new JTable();

        modeloHuespedes = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuespedes.addColumn("ID");
        modeloHuespedes.addColumn("Nombre");
        modeloHuespedes.addColumn("Apellido");
        modeloHuespedes.addColumn("Fecha de Nacimiento");
        modeloHuespedes.addColumn("Nacionalidad");
        modeloHuespedes.addColumn("Telefono");
        modeloHuespedes.addColumn("Id de Reserva");

        tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes, null);

        tbReservas = new JTable();

        modeloReservas = (DefaultTableModel) tbReservas.getModel();
        modeloReservas.addColumn("ID");
        modeloReservas.addColumn("Fecha de Entrada");
        modeloReservas.addColumn("Fecha de Salida");
        modeloReservas.addColumn("Valor");
        modeloReservas.addColumn("Forma de Pago");

        tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas, null);

        listar();

        JButton btnEliminar = new JButton("");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminar();
                limpiarTabla();
                listar();
            }
        });
        btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
        btnEliminar.setBackground(SystemColor.menu);
        btnEliminar.setBounds(651, 416, 54, 41);
        contentPane.add(btnEliminar);

        JButton btnCancelar = new JButton("");
        btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
        btnCancelar.setBackground(SystemColor.menu);
        btnCancelar.setBounds(713, 416, 54, 41);
        contentPane.add(btnCancelar);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(25, 10, 104, 107);
        contentPane.add(lblNewLabel_2);
        setResizable(false);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void listar() {
        List<Huesped> huespedes = this.huespedesController.listar();

        huespedes.forEach(huesped -> modeloHuespedes.addRow(new Object[]{
                huesped.getId(),
                huesped.getNombre(),
                huesped.getApellido(),
                huesped.getFechaNacimiento(),
                huesped.getNacionalidad(),
                huesped.getTelefono(),
                huesped.getReservaId()
        }));

        List<Reserva> reservas = this.reservasController.listar();

        reservas.forEach(reserva -> modeloReservas.addRow(new Object[]{
                reserva.getId(),
                reserva.getFechaEntrada(),
                reserva.getFechaSalida(),
                reserva.getValor(),
                reserva.getFormaPago()
        }));
    }

    private void limpiarTabla() {
        modeloHuespedes.getDataVector().clear();
        modeloReservas.getDataVector().clear();
    }

    private boolean huespedTieneFilaElegida() {
        //return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }

    private boolean reservaTieneFilaElegida() {
        //return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
        return tbReservas.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }

    private void modificar() {
        if (huespedTieneFilaElegida()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }


        Optional.ofNullable(modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresent(fila -> {
                    int id = Integer.parseInt(modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
                    String nombre = (String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 1);
                    String apellido = (String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 2);
                    String fechaNacimiento = modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
                    String nacionalidad = (String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 4);
                    String telefono = (String) modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 5);
                    int reservaId = Integer.parseInt(modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());

                    Huesped huesped = new Huesped(id, nombre, apellido, Date.valueOf(fechaNacimiento),
                            nacionalidad, telefono, reservaId);

                    int filasModificadas;

                    filasModificadas = this.huespedesController.modificar(huesped);

                    JOptionPane.showMessageDialog(this, "Se modificaron " +
                            filasModificadas + " filas");

                    //return;
                });
    }

    private void eliminar() {
        if (huespedTieneFilaElegida()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        //Falta recargar la tabla al eliminar un huesped

        Optional.ofNullable(modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresent(fila -> {
                    int id = Integer.parseInt(modeloHuespedes.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

                    int cantidadEliminada;

                    cantidadEliminada = this.huespedesController.eliminar(id);

                    JOptionPane.showMessageDialog(this, "Se eliminaron " +
                            cantidadEliminada + " filas");
                });
    }

}
