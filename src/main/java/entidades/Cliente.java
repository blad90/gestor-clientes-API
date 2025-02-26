package entidades;

public class Cliente {
    private Long id;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Correo correo;
    private Direccion direccion;
    private Telefono telefono;
    private Pais pais;
    private Gentilicio gentilicio;

    public Cliente() {
        this.primerNombre = "Fulano";
        this.segundoNombre = "DeTal";
        this.primerApellido = "Mendez";
        this.segundoApellido = "Ortiz";
        this.correo = new Correo();
        this.direccion = new Direccion();
        this.telefono = new Telefono();
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

    @Override
    public String toString() {
        return "Cliente{" +
                "primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", correo=" + correo +
                ", direccion=" + direccion +
                ", telefono=" + telefono +
                ", pais=" + pais +
                ", gentilicio=" + gentilicio +
                '}';
    }
}
