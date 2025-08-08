import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ProductoTest {

    @Test
    void aplicarImpuesto_valido_actualizaPrecio() {
        Producto p = new Producto(1L, "Test", 100.0);
        p.aplicarImpuesto(10);
        assertEquals(110.0, p.getPrecio());
    }

    @Test
    void aplicarImpuesto_fueraDeRango_lanzaExcepcion() {
        Producto p = new Producto(2L, "Test", 100.0);
        assertThrows(IllegalArgumentException.class, () -> p.aplicarImpuesto(60));
    }
}