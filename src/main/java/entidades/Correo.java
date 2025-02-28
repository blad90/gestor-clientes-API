package entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Correo {
    @Id
    @GeneratedValue
    private Long id;
    private String usuarioCorreo;

    public Correo() {
    }

    public Correo(String usuarioCorreo) {
        if(esCorreoValido(usuarioCorreo)){
            this.usuarioCorreo = usuarioCorreo;
        }
    }

    private boolean esCorreoValido(String correo){
       return correo.matches("^\\S+@\\S+\\.\\S+$");
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

    public void setUsuarioCorreo(String usuarioCorreo) {
        if(esCorreoValido(usuarioCorreo)) this.usuarioCorreo = usuarioCorreo;
    }
}
