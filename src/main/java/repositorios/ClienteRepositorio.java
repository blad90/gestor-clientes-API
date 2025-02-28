package repositorios;

import entidades.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

/** Representa el repositorio para el manejo de clientes a nivel de base de datos.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
@ApplicationScoped
public class ClienteRepositorio implements PanacheRepository<Cliente> {
}
