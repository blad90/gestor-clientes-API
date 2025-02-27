package servicios;

import entidades.Cliente;
import entidades.Pais;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import repositorios.ClienteRepositorio;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GestorClienteServicio {

    @Inject
    ClienteRepositorio clienteRepositorio;

    @Transactional
    public Response crearCliente(Cliente cliente){
        cliente = new Cliente();
        cliente.setPrimerNombre("Blad");
        cliente.setPrimerApellido("Baez");
        clienteRepositorio.persist(cliente);
        return Response.ok(cliente).status(201).build();
    }

    public Response obtenerClientes(){
        List<Cliente> clientesExistentes = clienteRepositorio.listAll();
        return Response.ok(clientesExistentes).build();
    }

    public Response obtenerClientesPorPais(String nombrePais){
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

    public Response obtenerClientePorId(String id){
        // TODO
        Cliente c1 = new Cliente();
        c1.setId(1000L);

        return Response.ok(c1).build();
    }

    public Response actualizarClientePorId(String id){
        //TODO
        return Response.ok().build();
    }

    public Response eliminarClientePorId(Long id){
        // TODO
        return Response.ok().build();
    }
}
