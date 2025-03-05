import com.fasterxml.jackson.core.JsonProcessingException;
import dto.ClienteDTO;
import entidades.*;
import excepciones.ClienteNoEncontradoException;
import excepciones.CorreoInvalidoException;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import servicios.GestorClienteServicio;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GestorClientesTest {

    @Inject
    GestorClienteServicio gestorClienteServicio;
    private ClienteDTO clienteDTOPrueba;

    @BeforeEach
    public void setUp() throws CorreoInvalidoException, JsonProcessingException {
        clienteDTOPrueba = new ClienteDTO();
        clienteDTOPrueba.setPrimerNombre("Juan");
        clienteDTOPrueba.setPrimerApellido("Perez");
        clienteDTOPrueba.setCorreo(new Correo("juan@email.com"));
        clienteDTOPrueba.setDireccion(new Direccion("Avenida Honduras", "Santo Domingo"));
        clienteDTOPrueba.setPais(new Pais("DOM"));
        clienteDTOPrueba.setTelefono(new Telefono("809-444-3332"));
        clienteDTOPrueba.setGentilicio(new Gentilicio(gestorClienteServicio.obtenerGentiliciosPorPais(clienteDTOPrueba.getPais().getCodigoPais())));

        gestorClienteServicio.crearCliente(clienteDTOPrueba);
    }

    @Test
    @DisplayName("Caso de prueba que valida la creacion del cliente")
    public void testCrearClienteRF01() {
        given()
                .contentType(ContentType.JSON)
                .body(clienteDTOPrueba)
                .when().post("/api/clientes/crear")
                .then()
                .statusCode(201);
    }

    @Test
    @DisplayName("Caso de prueba que valida la funcionalidad para acceder a los registros de todos los clientes")
    public void testObtenerClientesRF02(){
        given()
                .when().get("api/clientes/todos")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Caso de prueba que valida la funcionalidad para acceder a los registros de todos los clientes por pais (ej: DOM)")
    public void testObtenerClientesPorPaisRF03(){
        String codigoPaisPrueba = "DOM";
        given()
                .when().get("api/clientes/todos/" + codigoPaisPrueba)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Caso de prueba que valida la funcionalidad para a un cliente especifico por ID")
    public void testObtenerClienteCreadoPorIdRF04() {
        Assertions.assertTrue(gestorClienteServicio.obtenerClientePorId(1L).hasEntity());
    }

    @Test
    @DisplayName("Caso de prueba que valida la funcionalidad al momento de actualizar un cliente")
    public void testActualizarClienteCreadoPorIdRF05() throws CorreoInvalidoException {
        clienteDTOPrueba.setCorreo(new Correo("bb@email.com"));
        clienteDTOPrueba.setDireccion(new Direccion("Calle A", "Santo Domingo"));
        clienteDTOPrueba.setTelefono(new Telefono("809-888-1234"));
        clienteDTOPrueba.setPais(new Pais("PR"));

        Response respuesta = gestorClienteServicio.actualizarClientePorId(1L, clienteDTOPrueba);

        Assertions.assertEquals(respuesta.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    @DisplayName("Caso de prueba que valida la funcionalidad para eliminar un cliente por ID")
    public void testEliminarClientePorIdRF06() throws ClienteNoEncontradoException {
        Response respuesta = gestorClienteServicio.eliminarClientePorId(1L);
        Assertions.assertEquals(Response.Status.NO_CONTENT.getStatusCode(), respuesta.getStatus());
    }
}
