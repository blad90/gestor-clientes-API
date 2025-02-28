package entidades;

import excepciones.DatoInvalidoClienteException;
import excepciones.TelefonoInvalidoException;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/** Esta clase representa la entidad Cliente, la cual incluye
 * atributos basicos, asi como atributos compuestos como Correo,
 * Direccion, Telefono, Pais y Gentilicio.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
@Entity
public class Cliente extends PanacheEntity {
    @GeneratedValue
    private Long id;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Correo correo;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Direccion direccion;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Telefono telefono;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Pais pais;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Gentilicio gentilicio;

    public Cliente() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) throws DatoInvalidoClienteException {
        if(primerNombre == null || !primerNombre.matches("[a-zA-Z]+") || primerNombre.length() < 3) {
            throw new DatoInvalidoClienteException("El primer nombre del cliente es invalido.");
        } else {
            this.primerNombre = primerNombre;
        }
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) throws DatoInvalidoClienteException{
        if(segundoNombre == null || segundoNombre.isEmpty()) this.segundoNombre = "N/A";
        else if(!segundoNombre.matches("[a-zA-Z]+") || segundoNombre.length() < 3) {
            throw new DatoInvalidoClienteException("El segundo nombre del cliente es invalido.");
        } else {
            this.segundoNombre = segundoNombre;
        }
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) throws DatoInvalidoClienteException{
        if(primerApellido == null || !primerApellido.matches("[a-zA-Z]+") || primerApellido.length() < 3) {
            throw new DatoInvalidoClienteException("El primer apellido del cliente es invalido.");
        } else {
            this.primerApellido = primerApellido;
        }
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) throws DatoInvalidoClienteException{
        if(segundoApellido == null || segundoApellido.isEmpty()) this.segundoApellido = "N/A";
        else if(!segundoApellido.matches("[a-zA-Z]+") || segundoApellido.length() < 3) {
            throw new DatoInvalidoClienteException("El segundo apellido del cliente es invalido.");
        } else {
            this.segundoApellido = segundoApellido;
        }
    }

    public Correo getCorreo() {
        return correo;
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) throws TelefonoInvalidoException {
        this.telefono = telefono;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Gentilicio getGentilicio() {
        return gentilicio;
    }

    public void setGentilicio(Gentilicio gentilicio) {
        this.gentilicio = gentilicio;
    }
}
