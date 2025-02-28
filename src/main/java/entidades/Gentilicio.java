package entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/** Esta clase representa la entidad Gentilicio, que contiene informacion como atributos
 * sobre el gentilicio de un cliente en base al pais.
 *
 * @author Bladimir Baez
 * @version 1.0.0
 * */
@Entity
public class Gentilicio {
    @Id
    @GeneratedValue
    private Long id;
    private String descripcion;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
