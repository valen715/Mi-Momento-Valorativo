package co.com.edu.poli.ces3.tallerMetodos.controller;
import co.com.edu.poli.ces3.tallerMetodos.dto.DtoUsuario;
import co.com.edu.poli.ces3.tallerMetodos.model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class CtrUsuario {

    private Usuario modelUsuario;

    public CtrUsuario(){
        modelUsuario = new Usuario();
    }

    public DtoUsuario addUsuario(DtoUsuario usuario){
        try {
            Usuario nuevoUsuario = modelUsuario.create(usuario);
            return new DtoUsuario(nuevoUsuario.getId(), nuevoUsuario.getCorreo(), nuevoUsuario.getNombre(), nuevoUsuario.getContrasena());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<DtoUsuario> getAllUsuarios() {
        try {
            ArrayList<Usuario> usuarios = modelUsuario.all();
            ArrayList<DtoUsuario> dtoUsuarios = new ArrayList<>();

            for (Usuario usuario : usuarios) {
                DtoUsuario dtoUsuario = new DtoUsuario(
                        usuario.getId(),
                        usuario.getCorreo(),
                        usuario.getNombre(),
                        usuario.getContrasena()
                );
                dtoUsuarios.add(dtoUsuario);
            }

            return dtoUsuarios;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DtoUsuario getUsuarioById(int usuarioId) {
        try {
            Usuario usuario = modelUsuario.findById(usuarioId);
            if (usuario != null) {
                return new DtoUsuario(usuario.getId(), usuario.getCorreo(), usuario.getNombre(), usuario.getContrasena());
            } else {
                throw new RuntimeException("El usuario no se encuentra en la base de datos");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DtoUsuario updateUsuario(int usuarioId, DtoUsuario updateUsuario) {
        try {
            Usuario usuario = new Usuario(
                    usuarioId,
                    updateUsuario.getCorreo(),
                    updateUsuario.getNombre(),
                    updateUsuario.getContrasena()

            );

            Usuario updated = modelUsuario.update(usuario);
            return new DtoUsuario(updated.getId(), updated.getCorreo(), updated.getNombre(),updated.getContrasena());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUsuario(int usuarioId) {
        try {
            modelUsuario.delete(usuarioId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}