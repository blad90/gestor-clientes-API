package entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Correo {

    private String usuarioCorreo;
    @Id
    @GeneratedValue
    private Long id;


    public Correo() {
    }

    public Correo(String usuarioCorreo) {
        if(esCorreoValido(usuarioCorreo)){
            this.usuarioCorreo = usuarioCorreo;
        }
    }

    private boolean esCorreoValido(String correo){
        if(correo.matches("^/S+@/S+/./S+$")){
            System.out.println("Here");
            return true;
        }
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
