package entidades;

import excepciones.CorreoInvalidoException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/** Esta clase representa la entidad Correo, la cual
 * incluye un metodo de utilidad que permite validar
 * el usuario de correo mediante RegEx.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
@Entity
public class Correo {
    @Id
    @GeneratedValue
    private Long id;
    private String usuarioCorreo;

    public Correo() {
    }

    public Correo(String usuarioCorreo) throws CorreoInvalidoException {
        if(esCorreoValido(usuarioCorreo)){
            this.usuarioCorreo = usuarioCorreo;
        }
    }

    private boolean esCorreoValido(String correo) throws CorreoInvalidoException{
        if(correo.matches("^\\S+@\\S+\\.\\S+$")) return true;
        else {
            throw new CorreoInvalidoException("El correo: " + correo + " tiene un formato invalido.");
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) throws CorreoInvalidoException{
        if(esCorreoValido(usuarioCorreo)) this.usuarioCorreo = usuarioCorreo;
    }
}
