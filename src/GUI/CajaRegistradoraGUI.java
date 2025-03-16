package GUI;

import Pilas.CajaRegistradoraPila;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CajaRegistradoraGUI extends JFrame {
    // Componentes de la interfaz gráfica
    private JTextField txtNombre, txtCantidad, txtPrecio;
    private JTextArea txtAreaTicket;
    private JButton btnAgregar, btnEliminar, btnGenerarTicket;

    // Instancia de la lógica de las pilas
    private CajaRegistradoraPila cajaRegistradoraPila;

    public CajaRegistradoraGUI() {
        // Configuración básica de la ventana
        setTitle("Caja Registradora");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializamods la lógica de las pilas
        cajaRegistradoraPila = new CajaRegistradoraPila();

        //panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

       
        panel.add(new JLabel("Nombre del artículo:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panel.add(txtCantidad);

        panel.add(new JLabel("Precio unitario:"));
        txtPrecio = new JTextField();
        panel.add(txtPrecio);

        
        btnAgregar = new JButton("Agregar artículo");
        panel.add(btnAgregar);

        btnEliminar = new JButton("Eliminar artículo");
        panel.add(btnEliminar);

        btnGenerarTicket = new JButton("Generar Ticket");
        panel.add(btnGenerarTicket);

        // Área de texto para mostrar el ticket
        txtAreaTicket = new JTextArea();
        txtAreaTicket.setEditable(false);
        txtAreaTicket.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtAreaTicket);
        panel.add(scrollPane);

        // Agregar el panel a la ventana
        add(panel);

        // Configurar los eventos de los botones
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarArticulo();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarArticulo();
            }
        });

        btnGenerarTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarTicket();
            }
        });
    }

    // Método para agregar un artículo
    private void agregarArticulo() {
        try {
            String nombre = txtNombre.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            // Se valida que los campos no estén vacíos
            if (nombre.isEmpty() || txtCantidad.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Se agrega el artículo a la pila
            cajaRegistradoraPila.agregarArticulo(nombre, cantidad, precio);

            // Se limpian los campos de texto
            txtNombre.setText("");
            txtCantidad.setText("");
            txtPrecio.setText("");

            JOptionPane.showMessageDialog(this, "Artículo agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad y precio deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para eliminar un artículo
    private void eliminarArticulo() {
        cajaRegistradoraPila.eliminarArticulo();
        JOptionPane.showMessageDialog(this, "Último artículo eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para generar el ticket
    private void generarTicket() {
        String ticket = cajaRegistradoraPila.generarTicket();
        txtAreaTicket.setText(ticket);
    }
}