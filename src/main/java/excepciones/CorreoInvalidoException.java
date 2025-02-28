package excepciones;

/** Esta clase es un tipo de Exception utilizada para el manejo
 * de excepciones relacionadas al formato del correo.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
public class CorreoInvalidoException extends Exception {
    public CorreoInvalidoException(String message) {
        super(message);
    }
}
