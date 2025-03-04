package servicios;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "https://restcountries.com/v3.1/alpha")
public interface IGentilicioServicio {

    @GET
    @Path("/{codigoPais}")
    @Produces(MediaType.APPLICATION_JSON)
    String obtenerGentilicioPorPais(@PathParam("codigoPais") String codigoPais);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<String> obtenerGentilicios();
}
