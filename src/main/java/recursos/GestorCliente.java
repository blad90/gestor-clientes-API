package recursos;

import dto.ClienteDTO;
import entidades.Cliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import servicios.GestorClienteServicio;

@Path("/clientes")
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

    @GET()
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarClientePorId(@PathParam("idcliente") Long id){
        return gestorClienteServicio.actualizarClientePorId(id);
    }

    @DELETE
    @Path("eliminar/{idcliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarClientePorId(@PathParam("idcliente") Long id){
       return gestorClienteServicio.eliminarClientePorId(id);
    }
}