package excepciones;

/** Esta clase es un tipo de Exception utilizada para el manejo
 * de excepciones relacionadas al momento de no encontrar un cliente registrado.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
public class ClienteNoEncontradoException extends Exception {
    public ClienteNoEncontradoException(String message) {
        super(message);
    }
}
