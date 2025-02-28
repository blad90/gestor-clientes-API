package entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pais {
    @Id
    @GeneratedValue
    private Long id;
    private String nombrePais;
    private String codigoPais;

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombrePais='" + nombrePais + '\'' +
                ", codigoPais='" + codigoPais + '\'' +
                '}';
    }
}
