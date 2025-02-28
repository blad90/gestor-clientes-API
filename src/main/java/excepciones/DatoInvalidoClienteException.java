package excepciones;

/** Esta clase es un tipo de Exception utilizada para el manejo
 * de excepciones relacionadas al formato de los datos basicos del cliente.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
public class DatoInvalidoClienteException extends Exception {
    public DatoInvalidoClienteException(String message) {
        super(message);
    }
}
