package servicios;

import entidades.Gentilicio;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface IGentilicioServicio {

    @GET
    @Path("{codigoPais}")
    @Produces(MediaType.APPLICATION_JSON)
    Gentilicio obtenerGentilicioPorPais(@PathParam("codigoPais") String codigo);
}
