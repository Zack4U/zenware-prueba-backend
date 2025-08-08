# Prueba Técnica – Backend Developer (Java 8 + Quarkus)

**Tiempo sugerido:** 2 horas (máx. 3 h)  
**Entrega:** Repositorio Git con esta estructura y un README que incluya respuestas (ver más abajo).

## Estructura sugerida
```
repo/
 ├── app/               # tu código Quarkus
 ├── src/test/          # tests JUnit
 ├── README.md          # instrucciones para correr + respuestas secciones 3‑5
 ├── pom.xml            # build Maven de ejemplo
 └── logs/diagnostico.log
```
> Puedes usar Gradle si lo prefieres; ajusta el build a tu conveniencia.

---
## 1. Java Core (15 pts)
Implementa la clase `Producto` con:
* `id`, `nombre`, `precio`
* `aplicarImpuesto(double porcentaje)` – suma el impuesto al precio
  * Sólo acepta 0–50 %; de lo contrario lanza `IllegalArgumentException`.

### Tests mínimos
* Precio 100 → 10 % → 110
* Precio 100 → 60 % → excepción

---
## 2. Micro‑API Quarkus (45 pts)
Crea un servicio REST con estos endpoints:

| Verbo | Ruta                 | Descripción                          |
|-------|----------------------|--------------------------------------|
| GET   | `/productos`         | Lista todos los productos            |
| POST  | `/productos`         | Crea uno (JSON)                      |
| GET   | `/productos/{id}`    | Retorna 404 si no existe             |
| DELETE| `/productos/{id}`    | Elimina uno, 204 o 404               |

Requisitos:
* Persistencia **en memoria** (Map o Panache+H2).
* Bean Validation (`nombre` no vacío, `precio` ≥ 0).
* Manejo de error uniforme `{code,message}`.
* Dos tests JUnit (uno feliz + uno 404).

---
## 3. Logs (15 pts)
Revisa `logs/diagnostico.log` y responde en tu README:

1. ¿Qué causó la excepción?  
2. ¿Cómo la prevenirías?  
3. ¿Con qué nivel de log registrarías la entrada inválida y por qué?

---
## 4. AWS (15 pts)
* ¿Cuándo usar **EC2** vs **Lambda**? (máx. 3 líneas)
* Nombra 3 servicios de observabilidad y su propósito principal (1 línea c/u).

---
## 5. SQL (10 pts)
Con tabla `productos(id PK, nombre, precio)`:
* Consulta que retorne los 5 productos con `precio` > 100 000 ordenados desc.  
* Explica cuándo un `INDEX(nombre)` acelera búsquedas (1 línea).

---
## Entrega
1. Sube tu código a un repo (privado o público) y comparte el enlace.  
2. Incluye en `README.md`:
   * Pasos `mvn quarkus:dev`
   * Ejemplos `curl` para probar la API
   * Respuestas secciones **3, 4, 5**.  

**Corte para aprobar:** 70 pts sobre 100.

¡Éxitos!
