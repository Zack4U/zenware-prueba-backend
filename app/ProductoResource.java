import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {

    private static final Map<Long, Producto> productos = new ConcurrentHashMap<>();
    private static long idCounter = 1;

    public static class ProductoDTO {
        @NotBlank
        public String nombre;
        @Min(0)
        public double precio;
    }

    @GET
    public Collection<Producto> listar() {
        return productos.values();
    }

    @POST
    public Response crear(@Valid ProductoDTO dto) {
        long id = idCounter++;
        Producto p = new Producto(id, dto.nombre, dto.precio);
        productos.put(id, p);
        return Response.status(Response.Status.CREATED).entity(p).build();
    }

    @GET
    @Path("/{id}")
    public Response obtener(@PathParam("id") long id) {
        Producto p = productos.get(id);
        if (p == null) {
            return error(404, "Producto no encontrado");
        }
        return Response.ok(p).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") long id) {
        Producto p = productos.remove(id);
        if (p == null) {
            return error(404, "Producto no encontrado");
        }
        return Response.noContent().build();
    }

    private Response error(int code, String message) {
        return Response.status(code)
            .entity(Map.of("code", code, "message", message))
            .build();
    }
}