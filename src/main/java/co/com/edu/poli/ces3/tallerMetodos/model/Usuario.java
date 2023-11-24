package co.com.edu.poli.ces3.tallerMetodos.model;


import co.com.edu.poli.ces3.tallerMetodos.dto.DtoUsuario;
import co.com.edu.poli.ces3.tallerMetodos.servlet.UsuarioServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario extends Conexion implements CRUD{
    public int id;

    protected String correo;

    private String nombre;

    protected String contrasena;

    public Usuario(int id, String correo, String nombre, String contrasena){
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public Usuario(String correo){
        this.correo = correo;
    }

    public Usuario() {
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

    @Override
    public Usuario create(DtoUsuario usuario) throws SQLException {
        Connection cnn = this.getConexion();
        if(cnn != null) {
            String sql = "INSERT INTO usuarios(correo, nombre, contrasena) VALUES('"+usuario.getCorreo()+"', '"+usuario.getNombre()+"','"+usuario.getContrasena()+"')";
            this.correo = usuario.getCorreo();
            this.nombre = usuario.getNombre();
            this.contrasena = usuario.getContrasena();
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                this.id = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                cnn.close();
            }
            return this;
        }
        return null;
    }

    @Override
    public ArrayList<Usuario> all() {
        Connection cnn = this.getConexion();
        ArrayList<Usuario> usuarios = new ArrayList<>();

        if (cnn != null) {
            String sql = "SELECT id,correo,nombre,contrasena FROM usuarios";
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String correo = rs.getString("correo");
                    String contrasena = rs.getString("contrasena");
                    Usuario usuario = new Usuario(id, nombre, correo, contrasena);
                    usuarios.add(usuario);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (cnn != null) {
                        cnn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return usuarios;
        }
        return null;
    }



    @Override
    public Usuario findById(int usuarioId) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "SELECT id,correo,nombre,contrasena FROM usuarios WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, usuarioId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String correo = rs.getString("correo");
                        String nombre = rs.getString("nombre");
                        String contrasena = rs.getString("contrasena");
                        return new Usuario(id, correo, nombre, contrasena);
                    } else {
                        return null;
                    }
                }
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
        return null;
    }

    @Override
    public Usuario update(Usuario usuario) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "UPDATE usuarios SET correo = ?, nombre = ?, contrasena = ? WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setString(1, usuario.getCorreo());
                stmt.setString(2, usuario.getNombre());
                stmt.setString(3, usuario.getContrasena());
                stmt.setInt(4, usuario.getId());
                stmt.executeUpdate();
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
        return usuario;
    }

    @Override
    public void delete(int usuarioId) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "DELETE FROM usuarios WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, usuarioId);
                stmt.executeUpdate();
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
    }


}