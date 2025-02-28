package servicios;

import dto.ClienteDTO;
import entidades.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import recursos.GestorCliente;
import repositorios.ClienteRepositorio;

import java.util.List;

@ApplicationScoped
public class GestorClienteServicio {

    @Inject
    ClienteRepositorio clienteRepositorio;

    @Inject
    GestorCliente gestorCliente;

    @Inject
    @RestClient
    IGentilicioServicio gentilicioServicio;

    @Transactional
    public Response crearCliente(ClienteDTO clienteDTO){
        Cliente cliente = convertirACliente(clienteDTO);
        clienteRepositorio.persist(cliente);
        return Response.ok(cliente).status(Response.Status.CREATED).build();
    }

    public Response obtenerClientes(){
        List<ClienteDTO> clientesDTOExistentes = clienteRepositorio.listAll().stream().map(this::convertirAClienteDTO).toList();
        return Response.ok(clientesDTOExistentes).build();
    }

    public Response obtenerClientesPorPais(String codigoPais){
        List<ClienteDTO> clientesPorPais = clienteRepositorio
                .listAll()
                .stream()
                .filter(cliente -> cliente.getPais().getCodigoPais().equals(codigoPais))
                .map(this::convertirAClienteDTO).toList();
        return Response.ok(clientesPorPais).build();
    }

    public Response obtenerClientePorId(Long id){
        ClienteDTO clienteDTO = convertirAClienteDTO(clienteRepositorio.findById(id));
        if(clienteDTO == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(clienteDTO).build();
    }

    public Response actualizarClientePorId(Long id){
        Cliente clienteParaModificar = clienteRepositorio.findById(id);
        if(clienteParaModificar == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok().build();
    }

    @Transactional
    public Response eliminarClientePorId(Long id){
        Cliente clienteParaRemover = clienteRepositorio.findById(id);
        if(clienteParaRemover == null) return Response.status(Response.Status.NOT_FOUND).build();

        clienteRepositorio.delete(clienteParaRemover);

        return Response.ok().build();
    }

    private ClienteDTO convertirAClienteDTO(Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setPrimerNombre(cliente.getPrimerNombre());
        clienteDTO.setSegundoNombre(cliente.getSegundoNombre());
        clienteDTO.setPrimerApellido(cliente.getPrimerApellido());
        clienteDTO.setSegundoApellido(cliente.getSegundoApellido());
        clienteDTO.setCorreo(cliente.getCorreo());
        clienteDTO.setDireccion(cliente.getDireccion());
        clienteDTO.setTelefono(cliente.getTelefono());
        clienteDTO.setPais(cliente.getPais());
        clienteDTO.setGentilicio(cliente.getGentilicio());
        return clienteDTO;
    }

    private Cliente convertirACliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setPrimerNombre(clienteDTO.getPrimerNombre());
        cliente.setSegundoNombre(clienteDTO.getSegundoNombre());
        cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
        cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setPais(clienteDTO.getPais());
        cliente.setGentilicio(clienteDTO.getGentilicio());
        return cliente;
    }
}
