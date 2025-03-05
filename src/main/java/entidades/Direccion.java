package entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/** Esta clase representa la entidad Direccion, que incluye los datos basicos
 * de la direccion del cliente.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
@Entity
public class Direccion {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Calle es REQUERIDA")
    private String calle;
    @NotBlank(message = "Ciudad es REQUERIDA")
    private String ciudad;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Pais pais;

    public Direccion() {
    }

    public Direccion(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
