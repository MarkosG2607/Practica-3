package Listas;

public class Articulo {
    private String nombre;
    private int cantidad;
    private double precio;
    private double resultado;

    // Constructor
    public Articulo(String nombre, int cantidad, double precio, double resultado) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.resultado = resultado;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double getResultado() {
        return resultado;
    }
}