package servicios;

import dto.ClienteDTO;
import entidades.Cliente;
import entidades.Gentilicio;
import excepciones.ClienteNoEncontradoException;
import excepciones.DatoInvalidoClienteException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import controladores.GestorCliente;
import repositorios.ClienteRepositorio;

import java.util.ArrayList;
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
            List<ClienteDTO> clientesDTOExistentes = clienteRepositorio.listAll().stream().map(c -> {
                try {
                    return convertirAClienteDTO(c);
                } catch (DatoInvalidoClienteException e) {
                    throw new RuntimeException(e);
                }
            }).toList();
            return Response.ok(clientesDTOExistentes).build();
    }

    public Response obtenerClientesPorPais(String codigoPais) {
        List<ClienteDTO> clientesPorPais = new ArrayList<>();
        for(Cliente cliente: clienteRepositorio.listAll()){
            try{
                if(cliente.getPais().getCodigoPais().equals(codigoPais)){
                    clientesPorPais.add(convertirAClienteDTO(cliente));
                }
            } catch (DatoInvalidoClienteException e){
                return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
            }
        }
        return Response.ok(clientesPorPais).build();
    }

    public Response obtenerClientePorId(Long id) {
        Cliente clienteAObtener = clienteRepositorio.findById(id);
        ClienteDTO clienteDTO = new ClienteDTO();
        if (clienteAObtener != null) {
            try {
                clienteDTO = convertirAClienteDTO(clienteAObtener);
                return Response.ok(clienteDTO).build();
            } catch (DatoInvalidoClienteException e) {
//                throw new ClienteNoEncontradoException("Cliente con el ID# " + id + " no existe.");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Transactional
    public Response actualizarClientePorId(Long id, ClienteDTO clienteDTO) {
        Cliente clienteParaModificar = clienteRepositorio.findById(id);
        ClienteDTO clienteDTOActualizado = new ClienteDTO();

        if (clienteParaModificar != null) {
            clienteParaModificar.setCorreo(clienteDTO.getCorreo());
            clienteParaModificar.setDireccion(clienteDTO.getDireccion());
            clienteParaModificar.setTelefono(clienteDTO.getTelefono());
            clienteParaModificar.setPais(clienteDTO.getPais());
            clienteRepositorio.persist(clienteParaModificar);

            try {
                clienteDTOActualizado = convertirAClienteDTO(clienteParaModificar);
            } catch (DatoInvalidoClienteException e) {
                return Response.noContent().build();
            }
        } return Response.ok(clienteDTOActualizado).build();
    }

    @Transactional
    public Response eliminarClientePorId(Long id) throws ClienteNoEncontradoException {
        Cliente clienteParaRemover;

        clienteParaRemover = clienteRepositorio.findById(id);
        if (clienteParaRemover == null) {
            throw new ClienteNoEncontradoException("Cliente a remover con el ID# " + id + " no existe.");
        }
        clienteRepositorio.delete(clienteParaRemover);
        return Response.noContent().build();
    }

    /**
     * Metodo de utilidad que se encarga de convertir un objeto de tipo Cliente a version DTO
     * garantizando la integridad de los atributos de la entidad y que no puedan ser modificados
     * directamente.
     *
     * @param cliente representa el objeto de la entidad Cliente
     * @return ClienteDTO
     */
    private ClienteDTO convertirAClienteDTO(Cliente cliente) throws DatoInvalidoClienteException {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        try {
            clienteDTO.setPrimerNombre(cliente.getPrimerNombre());
            clienteDTO.setSegundoNombre(cliente.getSegundoNombre() != null ? cliente.getSegundoNombre() : "");
            clienteDTO.setPrimerApellido(cliente.getPrimerApellido());
            clienteDTO.setSegundoApellido(cliente.getSegundoApellido() != null ? cliente.getSegundoApellido() : "");
            clienteDTO.setCorreo(cliente.getCorreo());
            clienteDTO.setDireccion(cliente.getDireccion());
            clienteDTO.setTelefono(cliente.getTelefono());
            clienteDTO.setPais(cliente.getPais());
            clienteDTO.setGentilicio(cliente.getGentilicio());
        } catch (DatoInvalidoClienteException e) {
            //return null;
        }

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
            cliente.setSegundoNombre(clienteDTO.getSegundoNombre() != null ? clienteDTO.getSegundoNombre() : "");
            cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
            cliente.setSegundoApellido(clienteDTO.getSegundoApellido() != null ? clienteDTO.getSegundoApellido() : "");
            cliente.setCorreo(clienteDTO.getCorreo());
            cliente.setDireccion(clienteDTO.getDireccion());
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setPais(clienteDTO.getPais());
            cliente.setGentilicio(clienteDTO.getGentilicio());
        } catch (DatoInvalidoClienteException e) {
            //return null;
        }
        return cliente;
    }

    public String obtenerGentiliciosPorPais(String codigoPais){
        String gentilicio = gentilicioServicio.obtenerGentilicioPorPais(codigoPais);
        return gentilicio;
    }
}
