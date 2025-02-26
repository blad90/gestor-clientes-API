package entidades;

public class Pais {
    private String nombrePais;

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "nombrePais='" + nombrePais + '\'' +
                '}';
    }
}
