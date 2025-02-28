package entidades;

import excepciones.TelefonoInvalidoException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/** Esta clase representa la entidad Telefono, la cual
 * incluye un metodo de utilidad que permite validar
 * el telefono mediante el uso de RegEx.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
@Entity
public class Telefono {
    @Id
    @GeneratedValue
    private Long id;
    private String numeroTelefono;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) throws TelefonoInvalidoException {
        if(numeroTelefono.matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$")) {
            this.numeroTelefono = numeroTelefono;
        } else {
            throw new TelefonoInvalidoException("El numero provisto : " + numeroTelefono + " no tiene un formato valido. \nEjemplos:" +
                    "Ej1: 809-555-5555\n");
        }

    }
}
