package entidades;

import excepciones.DatoInvalidoClienteException;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;

/**
 * Esta clase representa la entidad Cliente, la cual incluye
 * atributos basicos, asi como atributos compuestos como Correo,
 * Direccion, Telefono, Pais y Gentilicio.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 */
@Entity
public class Cliente extends PanacheEntity implements Serializable {
    @GeneratedValue
    private Long id;
    @NotBlank(message = "El primer nombre es REQUERIDO")
    private String primerNombre;
    private String segundoNombre;
    @NotBlank(message = "El primer apellido es REQUERIDO")
    private String primerApellido;
    private String segundoApellido;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Correo correo;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Direccion direccion;

    @OneToOne
    @Cascade({CascadeType.ALL})
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

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        if (segundoNombre != null) return segundoNombre;
        return "";
    }

    public void setSegundoNombre(String segundoNombre) throws DatoInvalidoClienteException {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) throws DatoInvalidoClienteException {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) throws DatoInvalidoClienteException {
        this.segundoApellido = segundoApellido;
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

    public void setTelefono(Telefono telefono) {
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
