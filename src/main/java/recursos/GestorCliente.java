package recursos;

import entidades.Cliente;
import entidades.Pais;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/clientes")
public class GestorCliente {

    @GET()
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerClientes(){
        // TODO Ejemplo inicial...
        List<Cliente> clientesExistentes = new ArrayList<>();
        clientesExistentes.add(new Cliente());
        clientesExistentes.add(new Cliente());
        clientesExistentes.add(new Cliente());
        return Response.ok(clientesExistentes).build();
    }

    @GET()
    @Path("pais/todos/{pais}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerClientesPorPais(@PathParam(value = "pais") String nombrePais){
        // TODO Ejemplo inicial...
        List<Cliente> clientesExistentes = new ArrayList<>();
        Cliente c1 = new Cliente();
        Pais p1 = new Pais();
        p1.setNombrePais(nombrePais);
        c1.setPais(p1);

        Cliente c2 = new Cliente();
        Pais p2 = new Pais();
        p2.setNombrePais("PR");
        c2.setPais(p2);

        clientesExistentes.add(c1);
        clientesExistentes.add(c2);

        return Response.ok(clientesExistentes).build();
    }

    @GET
    @Path("id/{idcliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerClientePorId(@PathParam("idcliente") String id){
        // TODO
        Cliente c1 = new Cliente();
        c1.setId(1000L);

        return Response.ok(c1).build();
    }

    @PATCH
    @Path("id/actualizar/{correo}/{direccion}/{telefono}/{pais}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarClientePorId(@PathParam("correo") String correo,
                                           @PathParam("direccion") String direccion,
                                           @PathParam("telefono") String telefono,
                                           @PathParam("pais") String pais){
        //TODO
        return Response.ok().build();
    }

    @DELETE
    @Path("eliminar/{idcliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarClientePorId(@PathParam("idcliente") Long id){
        // TODO
        return Response.ok().build();
    }
}