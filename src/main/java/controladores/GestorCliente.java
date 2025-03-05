package controladores;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.ClienteDTO;
import jakarta.validation.ConstraintViolation;
import utils.GentilicioExtractorJson;
import excepciones.ClienteNoEncontradoException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.GestorClienteServicio;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Esta clase actua como controlador y posee los endpoints principales de la aplicacion.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 */

@Path("/api/clientes")
public class GestorCliente {

    @Inject
    GestorClienteServicio gestorClienteServicio;

    @POST
    @Path("crear")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCliente(ClienteDTO nuevoClienteDTO){
        return gestorClienteServicio.crearCliente(nuevoClienteDTO);
    }

    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerClientes(){
        return gestorClienteServicio.obtenerClientes();
    }

    @GET()
    @Path("todos/{pais}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerClientesPorPais(@PathParam(value = "pais") String nombrePais){
        return gestorClienteServicio.obtenerClientesPorPais(nombrePais);
    }

    @GET
    @Path("id/{idcliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerClientePorId(@PathParam("idcliente") Long id){
            return gestorClienteServicio.obtenerClientePorId(id);
    }

    @PATCH
    @Path("actualizar/{idcliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarClientePorId(@PathParam("idcliente") Long id, ClienteDTO clienteDTO){
            return gestorClienteServicio.actualizarClientePorId(id, clienteDTO);
    }

    @DELETE
    @Path("eliminar/{idcliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarClientePorId(@PathParam("idcliente") Long id) {
        try {
            return gestorClienteServicio.eliminarClientePorId(id);
        } catch (ClienteNoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{codigoPais}")
    public Response obtenerGentilicioPorPais(@PathParam("codigoPais") String codigoPais) throws JsonProcessingException {
        String gentilicio = GentilicioExtractorJson.extraerValorGentilicio(gestorClienteServicio.obtenerGentiliciosPorPais(codigoPais));
        return Response.ok(gentilicio).build();
    }
}