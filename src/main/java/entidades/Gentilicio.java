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
    private String gentilicio;

    public Gentilicio() {
    }

    public Gentilicio(String gentilicio) {
        this.gentilicio = gentilicio;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getGentilicio() {
        return gentilicio;
    }

    public void setGentilicio(String gentilicio) {
        this.gentilicio = gentilicio;
    }

    @Override
    public String toString() {
        return "Gentilicio{" +
                "id=" + id +
                ", gentilicio='" + gentilicio + '\'' +
                '}';
    }
}
