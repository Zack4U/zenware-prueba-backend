import java.util.Objects;

public class Producto {
    private final Long id;
    private final String nombre;
    private double precio;

    public Producto(Long id, String nombre, double precio) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    /**
     * Aplica un impuesto al precio actual.
     * @param porcentaje porcentaje de impuesto (0-50)
     * @throws IllegalArgumentException si el porcentaje está fuera de rango
     */
    public void aplicarImpuesto(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 50) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 50");
        }
        this.precio += this.precio * porcentaje / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + "}";
    }
}
