package co.com.edu.poli.ces3.tallerMetodos.dto;

public class DtoUsuario {
    public int id;

    protected String correo;

    private String nombre;

    protected String contrasena;

    public DtoUsuario(int id, String correo, String nombre, String contrasena){
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;

    }

    public DtoUsuario(String correo, String nombre, String contrasena){
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public DtoUsuario() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "El usuario se llama: " + this.nombre +
                " su correo es: " + this.correo;
    }
}