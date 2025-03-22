package GUI;

import Listas.CajaRegistradoraLista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CajaRegistradoraGUI extends JFrame {
    private JTextField txtNombre, txtCantidad, txtPrecio;
    private JTextArea txtAreaTicket;
    private JButton btnAgregar, btnEliminar, btnGenerarTicket;

    // Instancia de la lógica de las listas
    private CajaRegistradoraLista cajaRegistradoraLista;

    public CajaRegistradoraGUI() {
        setTitle("Caja Registradora");
        setSize(600, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Inicializar la lógica de las listas
        cajaRegistradoraLista = new CajaRegistradoraLista();

        
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10)); 

        
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 10));

        // Campos de texto para ingresar datos
        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelCampos.add(txtCantidad);

        panelCampos.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelCampos.add(txtPrecio);

        
        panelPrincipal.add(panelCampos, BorderLayout.NORTH);

        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centrar los botones

        // Botones para agregar, eliminar y generar el ticket
        btnAgregar = new JButton("Agregar");
        panelBotones.add(btnAgregar);

        btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnEliminar);

        btnGenerarTicket = new JButton("Generar Ticket");
        panelBotones.add(btnGenerarTicket);

        
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        
        txtAreaTicket = new JTextArea();
        txtAreaTicket.setEditable(false); // El usuario no puede editar el ticket
        txtAreaTicket.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
        JScrollPane scrollPane = new JScrollPane(txtAreaTicket);
        scrollPane.setPreferredSize(new Dimension(580, 400)); 

        
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        
        add(panelPrincipal);

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

            // Validar que los campos no estén vacíos
            if (nombre.isEmpty() || txtCantidad.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Agregar el artículo a la lista
            cajaRegistradoraLista.agregarArticulo(nombre, cantidad, precio);

            // Limpiar los campos de texto
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
        // Verificar si hay artículos en la lista
        if (cajaRegistradoraLista.getListaArticulos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay artículos para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            // Eliminar el último artículo de la lista
            cajaRegistradoraLista.eliminarArticulo(cajaRegistradoraLista.getListaArticulos().size() - 1);
            JOptionPane.showMessageDialog(this, "Último artículo eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para generar el ticket
    private void generarTicket() {
        String ticket = cajaRegistradoraLista.generarTicket();
        txtAreaTicket.setText(ticket); // Mostrar el ticket en el área de texto
    }
}