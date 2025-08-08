import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

@QuarkusTest
public class ProductoResourceTest {

    @Test
    void crearYObtenerProducto_exitoso() {
        // Crear producto
        long id = ((Integer) given()
                .contentType(ContentType.JSON)
                .body("{\"nombre\":\"Lapiz\",\"precio\":10}")
                .when()
                .post("/productos")
                .then()
                .statusCode(201)
                .body("nombre", equalTo("Lapiz"))
                .body("precio", equalTo(10f))
                .extract().path("id")).longValue();

        // Obtener producto
        given()
                .when()
                .get("/productos/" + id)
                .then()
                .statusCode(200)
                .body("nombre", equalTo("Lapiz"))
                .body("precio", equalTo(10f));
    }

    @Test
    void obtenerProducto_noExiste_retorna404() {
        given()
                .when()
                .get("/productos/9999")
                .then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("message", containsString("no encontrado"));
    }

    @Test
    void listarProductos_devuelveLista() {
        // Crear dos productos
        given()
                .contentType(ContentType.JSON)
                .body("{\"nombre\":\"Borrador\",\"precio\":5}")
                .post("/productos")
                .then()
                .statusCode(201);

        given()
                .contentType(ContentType.JSON)
                .body("{\"nombre\":\"Cuaderno\",\"precio\":20}")
                .post("/productos")
                .then()
                .statusCode(201);

        // Listar productos
        given()
                .when()
                .get("/productos")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body("[0].nombre", containsString("Borrador"))
                .body("[1].nombre", containsString("Cuaderno"));
    }

    @Test
    void crearProducto_nombreVacio_retorna400() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"nombre\":\"\",\"precio\":10}")
                .post("/productos")
                .then()
                .statusCode(400)
                .body("code", equalTo(400))
                .body("message", containsString("nombre"));
    }

    @Test
    void eliminarProducto_exitoso() {
        // Crear producto
        int id = given()
                .contentType(ContentType.JSON)
                .body("{\"nombre\":\"Tijeras\",\"precio\":15}")
                .post("/productos")
                .then()
                .statusCode(201)
                .extract().path("id");

        // Eliminar producto
        given()
                .when()
                .delete("/productos/" + id)
                .then()
                .statusCode(204);

        // Verificar que ya no existe
        given()
                .when()
                .get("/productos/" + id)
                .then()
                .statusCode(404);
    }

    @Test
    void eliminarProducto_noExiste_retorna404() {
        given()
                .when()
                .delete("/productos/9999")
                .then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("message", containsString("no encontrado"));
    }
}