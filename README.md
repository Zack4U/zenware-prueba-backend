# prueba-backend

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Running tests

To execute all unit and integration tests, run:

```shell
./mvnw test
```

On Windows, use:

```shell
mvnw test
```

This will run all JUnit and Rest-Assured tests and show the results in the terminal.

## Pruebas CURL para la API

### Crear un producto
```sh
curl -X POST -H "Content-Type: application/json" -d '{"nombre":"Lapiz","precio":10}' http://localhost:8080/productos
```

### Listar todos los productos
```sh
curl http://localhost:8080/productos
```

### Obtener un producto por ID (ejemplo con id=1)
```sh
curl http://localhost:8080/productos/1
```

### Eliminar un producto por ID (ejemplo con id=1)
```sh
curl -X DELETE http://localhost:8080/productos/1
```

## Dependencias EXTRA para validación

Para habilitar la validación de datos en los endpoints REST y el manejo automático de errores de Bean Validation, se agregaron las siguientes dependencias en el `pom.xml`:

```xml
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>3.0.2</version>
</dependency>
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-hibernate-validator</artifactId>
</dependency>
```

- `jakarta.validation-api`: Provee las anotaciones estándar de Bean Validation (`@NotBlank`, `@Min`, etc.).
- `quarkus-hibernate-validator`: Integra Hibernate Validator con Quarkus para validar automáticamente los datos recibidos en los endpoints y activar el manejo de errores con `ExceptionMapper`.
- 
# zenware-prueba-backend

## 3. Logs (15 pts) 

**¿Qué causó la excepción?**  
La excepción `java.lang.NumberFormatException: For input string: "abc"` fue causada porque el código intentó convertir la cadena `"abc"` a un número entero usando `Integer.parseInt`, pero `"abc"` no es un valor numérico válido.

**¿Cómo la prevenirías?**  
Validaría previamente la entrada antes de intentar convertirla, usando una expresión regular o un método como `StringUtils.isNumeric` (de Apache Commons) para asegurar que la cadena contiene solo dígitos. También usaría manejo de excepciones (`try-catch`) para capturar el error y responder apropiadamente.

**¿Con qué nivel de log registrarías la entrada inválida y por qué?**  
Registrar la entrada inválida con nivel **WARN**.  
Esto indica que ocurrió un problema esperado (entrada incorrecta del usuario), pero no es un error crítico del sistema.  
Si la entrada proviene de una fuente externa o usuario, es útil para monitoreo sin saturar los logs de errores.

## 4. AWS (15 pts)
**¿Cuándo usar EC2 vs Lambda? (máx. 3 líneas)** 
- EC2 para aplicaciones que requieren servidores dedicados, procesos de larga duración o control total del entorno.  
- Lambda para ejecutar funciones bajo demanda y aplicaciones serverless.

**Nombra 3 servicios de observabilidad y su propósito principal (1 línea c/u).**
- CloudWatch: Monitoreo y alertas de logs, métricas y recursos AWS.  
- AWS Config: Auditoría y seguimiento de cambios en la configuración de recursos.  
- AWS Trusted Advisor: Recomendaciones de seguridad, costos y rendimiento para recursos AWS.

---
## 5. SQL (10 pts)
Con tabla `productos(id PK, nombre, precio)`:
**Consulta que retorne los 5 productos con `precio` > 100 000 ordenados desc.**
```sql
SELECT * FROM productos 
WHERE precio > 100000 
ORDER BY precio DESC 
LIMIT 5;
``` 

**Explica cuándo un `INDEX(nombre)` acelera búsquedas (1 línea)**

Un `INDEX(nombre)` acelera búsquedas cuando se realizan consultas filtrando o ordenando por el campo `nombre`, ya que permite localizar los registros más rápido sin escanear toda la tabla.

