package Pilas;

import java.util.Stack;

public class CajaRegistradoraPila {
    // Pila para almacenar los artículos
    private Stack<Articulo> pilaArticulos;

    // Constructor
    public CajaRegistradoraPila() {
        pilaArticulos = new Stack<>();
    }

    // Método para agregar un artículo a la pila
    public void agregarArticulo(String nombre, int cantidad, double precio) {
        double resultado = cantidad * precio; // Se calcula el resultado (cantidad * precio)
        Articulo articulo = new Articulo(nombre, cantidad, precio, resultado);
        pilaArticulos.push(articulo); // Se agrega el artículo a la pila
    }

    // Método para eliminar el último artículo agregado
    public void eliminarArticulo() {
        if (!pilaArticulos.isEmpty()) {
            pilaArticulos.pop(); // Eliminar el último artículo de la pila
        } else {
            System.out.println("No hay artículos para eliminar.");
        }
    }

    // Método para calcular el importe total
    public double calcularImporteTotal() {
        double importeTotal = 0;
        for (Articulo articulo : pilaArticulos) {
            importeTotal += articulo.getResultado();
        }
        return importeTotal;
    }

    // Método para aplicar descuentos (20% si se compran más de 5 artículos del mismo producto)
    public double aplicarDescuentos() {
        double descuento = 0;
        for (Articulo articulo : pilaArticulos) {
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
    ticket.append("=== Tienda 'Abarrotes' ===\n");
    ticket.append("Dirección: Calle Encinos 123, EDO MEX, Mexico\n");
    ticket.append("Teléfono: +123 456 7890\n");
    ticket.append("==================================\n");

    // Cuerpo del ticket
    ticket.append("Artículo\tCantidad\tPrecio\tResultado\n");
    ticket.append("----------------------------------\n");

    for (Articulo articulo : pilaArticulos) {
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
    }