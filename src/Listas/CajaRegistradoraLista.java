package Listas;

import java.util.ArrayList;

public class CajaRegistradoraLista {
    // Lista para almacenar los artículos
    private ArrayList<Articulo> listaArticulos;

    // Constructor
    public CajaRegistradoraLista() {
        listaArticulos = new ArrayList<>();
    }

    // Método para agregar un artículo a la lista
    public void agregarArticulo(String nombre, int cantidad, double precio) {
        double resultado = cantidad * precio; // Calcular el resultado (cantidad * precio)
        Articulo articulo = new Articulo(nombre, cantidad, precio, resultado);
        listaArticulos.add(articulo); // Agregar el artículo a la lista
    }

    // Método para eliminar un artículo por índice
    public void eliminarArticulo(int indice) {
        if (indice >= 0 && indice < listaArticulos.size()) {
            listaArticulos.remove(indice); // Eliminar el artículo en la posición indicada
        } else {
            System.out.println("Índice no válido.");
        }
    }

    // Método para calcular el importe total
    public double calcularImporteTotal() {
        double importeTotal = 0;
        for (Articulo articulo : listaArticulos) {
            importeTotal += articulo.getResultado();
        }
        return importeTotal;
    }

    // Método para aplicar descuentos (20% si se compran más de 5 artículos del mismo producto)
    public double aplicarDescuentos() {
        double descuento = 0;
        for (Articulo articulo : listaArticulos) {
            if (articulo.getCantidad() > 5) {
                descuento += articulo.getResultado() * 0.20; // Aplicar 20% de descuento
            }
        }
        return descuento;
    }

    // Método para generar el ticket
    public String generarTicket() {
        StringBuilder ticket = new StringBuilder();

        // Encabezado del ticket
        ticket.append("=== Tienda 'Mi Tienda' ===\n");
        ticket.append("Dirección: Calle Falsa 123, Ciudad, País\n");
        ticket.append("Teléfono: +123 456 7890\n");
        ticket.append("==================================\n");

        // Cuerpo del ticket
        ticket.append("Artículo\tCantidad\tPrecio\tResultado\n");
        ticket.append("----------------------------------\n");

        for (Articulo articulo : listaArticulos) {
            ticket.append(articulo.getNombre()).append("\t")
                  .append(articulo.getCantidad()).append("\t")
                  .append("$").append(articulo.getPrecio()).append("\t")
                  .append("$").append(articulo.getResultado()).append("\n");
        }

        // Cálculo del descuento y el importe total
        double importeTotal = calcularImporteTotal();
        double descuento = aplicarDescuentos();
        double totalAPagar = importeTotal - descuento;

        ticket.append("----------------------------------\n");
        ticket.append("Descuento aplicado: $").append(descuento).append("\n");
        ticket.append("Importe Total: $").append(importeTotal).append("\n");
        ticket.append("Total a Pagar: $").append(totalAPagar).append("\n");

        // Pie de página del ticket
        ticket.append("==================================\n");
        ticket.append("¡Gracias por su compra!\n");

        return ticket.toString();
    }

    // Método para obtener la lista de artículos
    public ArrayList<Articulo> getListaArticulos() {
        return listaArticulos;
    }
}