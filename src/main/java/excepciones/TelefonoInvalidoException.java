package excepciones;

/** Esta clase es un tipo de Exception utilizada para el manejo
 * de excepciones relacionadas al formato del telefono.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
public class TelefonoInvalidoException extends Exception {
    public TelefonoInvalidoException(String message) {
        super(message);
    }
}
