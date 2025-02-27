package entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

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

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Direccion> direcciones;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Telefono> telefonos;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Pais pais;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Gentilicio gentilicio;

    public Cliente() {
        this.primerNombre = "Fulano";
        this.segundoNombre = "DeTal";
        this.primerApellido = "Mendez";
        this.segundoApellido = "Ortiz";
        this.correo = new Correo();
//        this.direccion = new Direccion();
//        this.telefono = new Telefono();
        this.pais = new Pais();
        this.gentilicio = new Gentilicio();
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
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Correo getCorreo() {
        return correo;
    }

    public void setCorreo(Correo correo) {
        this.correo = correo;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
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

    @Override
    public String toString() {
        return "Cliente{" +
                "primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", correo=" + correo +
                ", direccion=" + direcciones +
                ", telefono=" + telefonos +
                ", pais=" + pais +
                ", gentilicio=" + gentilicio +
                '}';
    }
}
