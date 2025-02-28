package servicios;

import dto.ClienteDTO;
import entidades.Cliente;
import excepciones.ClienteNoEncontradoException;
import excepciones.CorreoInvalidoException;
import excepciones.DatoInvalidoClienteException;
import excepciones.TelefonoInvalidoException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import recursos.GestorCliente;
import repositorios.ClienteRepositorio;

import java.util.List;

/**
 * Esta clase es responsable de realizar las operaciones relacionadas
 * al cliente, que incluye crear, acceder a un cliente por identificador, modificar, remover,
 * y otros metodos de utilidad.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 */

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
    public Response crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertirACliente(clienteDTO);
        clienteRepositorio.persist(cliente);
        return Response.ok(cliente).status(Response.Status.CREATED).build();
    }

    public Response obtenerClientes() {
        List<ClienteDTO> clientesDTOExistentes = clienteRepositorio.listAll().stream().map(this::convertirAClienteDTO).toList();
        return Response.ok(clientesDTOExistentes).build();
    }

    public Response obtenerClientesPorPais(String codigoPais) {
        List<ClienteDTO> clientesPorPais = clienteRepositorio
                .listAll()
                .stream()
                .filter(cliente -> cliente.getPais().getCodigoPais().equals(codigoPais))
                .map(this::convertirAClienteDTO).toList();
        return Response.ok(clientesPorPais).build();
    }

    public Response obtenerClientePorId(Long id) throws ClienteNoEncontradoException{
        Cliente clienteAObtener = clienteRepositorio.findById(id);
        ClienteDTO clienteDTO;
        if (clienteAObtener != null) {
            clienteDTO = convertirAClienteDTO(clienteAObtener);
            return Response.ok(clienteDTO).build();
        } else throw new ClienteNoEncontradoException("Cliente con el ID# " + id + " no existe.");
    }

    @Transactional
    public Response actualizarClientePorId(Long id, ClienteDTO clienteDTO) throws ClienteNoEncontradoException, TelefonoInvalidoException{
        Cliente clienteParaModificar = clienteRepositorio.findById(id);
        ClienteDTO clienteDTOActualizado;
        if (clienteParaModificar != null) {
            clienteParaModificar.setCorreo(clienteDTO.getCorreo());
            clienteParaModificar.setDireccion(clienteDTO.getDireccion());
            clienteParaModificar.setTelefono(clienteDTO.getTelefono());
            clienteParaModificar.setPais(clienteDTO.getPais());
            clienteRepositorio.persist(clienteParaModificar);

            clienteDTOActualizado = convertirAClienteDTO(clienteParaModificar);
            return Response.ok(clienteDTOActualizado).build();
        } else throw new ClienteNoEncontradoException("Cliente a actualizar con el ID# " + id + " no existe.");
    }

    @Transactional
    public Response eliminarClientePorId(Long id) throws ClienteNoEncontradoException {
        Cliente clienteParaRemover;

        clienteParaRemover = clienteRepositorio.findById(id);
        if (clienteParaRemover == null) {
            throw new ClienteNoEncontradoException("Cliente a remover con el ID# " + id + " no existe.");
        }
        clienteRepositorio.delete(clienteParaRemover);
        return Response.ok().build();
    }

    /**
     * Metodo de utilidad que se encarga de convertir un objeto de tipo Cliente a version DTO
     * garantizando la integridad de los atributos de la entidad y que no puedan ser modificados
     * directamente.
     *
     * @param cliente representa el objeto de la entidad Cliente
     * @return ClienteDTO
     */
    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
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

    /**
     * Metodo de utilidad que se encarga de convertir un objeto DTO a version Cliente
     * con el fin de persistir el objeto original en el repositorio.
     *
     * @param clienteDTO representa la version DTO a convertir del objeto de la entidad Cliente
     * @return Cliente
     */
    private Cliente convertirACliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        try {
            cliente.setPrimerNombre(clienteDTO.getPrimerNombre());
            cliente.setSegundoNombre(clienteDTO.getSegundoNombre());
            cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
            cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
            cliente.setCorreo(clienteDTO.getCorreo());
            cliente.setDireccion(clienteDTO.getDireccion());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setPais(clienteDTO.getPais());
            cliente.setGentilicio(clienteDTO.getGentilicio());
        } catch (DatoInvalidoClienteException | TelefonoInvalidoException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}
