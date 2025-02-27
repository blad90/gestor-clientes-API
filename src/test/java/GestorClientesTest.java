import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class GestorClientesTest {
    @Test
    public void testObtenerClientesEndpoint(){
        given()
                .when().get("clientes/todos")
                .then()
                .statusCode(200);
    }
}
