import dto.ClienteDTO;
import entidades.*;
import excepciones.ClienteNoEncontradoException;
import excepciones.CorreoInvalidoException;
import excepciones.DatoInvalidoClienteException;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositorios.ClienteRepositorio;
import servicios.GestorClienteServicio;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class GestorClientesTest {

    @Inject
    GestorClienteServicio gestorClienteServicio;

    @Inject
    ClienteRepositorio clienteRepositorio;

    @Test
    public void testCrearCliente01() throws CorreoInvalidoException, DatoInvalidoClienteException {
        ClienteDTO clientePrueba = new ClienteDTO();
        clientePrueba.setPrimerNombre("Juan");
        clientePrueba.setPrimerApellido("Perez");

        clientePrueba.setCorreo(new Correo("prueba@email.com"));
        clientePrueba.setTelefono(new Telefono("809-444-3322"));
        clientePrueba.setPais(new Pais("DOM"));
        clientePrueba.setGentilicio(new Gentilicio());

        given()
                .contentType(ContentType.JSON)
                .body(clientePrueba)
                .when().post("/api/clientes/crear")
                .then()
                .statusCode(201);
    }

    @Test
    public void testAccesoClienteCreadoPorId() throws ClienteNoEncontradoException, DatoInvalidoClienteException {
        ClienteDTO clientePruebaDTO = new ClienteDTO();
        clientePruebaDTO.setPrimerNombre("Juan");
        clientePruebaDTO.setPrimerApellido("Perez");
        gestorClienteServicio.crearCliente(clientePruebaDTO);

        Assertions.assertTrue(gestorClienteServicio.obtenerClientePorId(1L).hasEntity());
    }

    @Test
    public void testObtenerClientesEndpoint(){
        given()
                .when().get("api/clientes/todos")
                .then()
                .statusCode(200);
    }
}
