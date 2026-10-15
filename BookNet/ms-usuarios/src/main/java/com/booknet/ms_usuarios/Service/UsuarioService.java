package com.booknet.ms_usuarios.Service;

import com.booknet.ms_usuarios.Model.Roles;
import com.booknet.ms_usuarios.Model.Usuario;
import com.booknet.ms_usuarios.Repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // LISTAR TODOS
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // LISTAR ACTIVOS
    public List<Usuario> listarActivos() {
        return usuarioRepository.findByActivoTrue();
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("El usuario con ID {} no existe.", id);
                    return new RuntimeException("El usuario no existe.");
                });
    }

    // BUSCAR POR EMAIL
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("El usuario con email {} no existe.", email);
                    return new RuntimeException("El usuario no existe.");
                });
    }

    // LISTAR POR ROL
    public List<Usuario> listarPorRol(Roles rol) {
        return usuarioRepository.findByRol(rol);
    }

    // CREAR
    public Usuario crear(Usuario usuario) {
        log.info("Creando usuario con email: {}", usuario.getEmail());

        // Validación para evitar emails duplicados
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            log.warn("Intento de crear usuario con email duplicado: {}", usuario.getEmail());
            throw new RuntimeException("Ya existe un usuario registrado con ese email.");
        }

        return usuarioRepository.save(usuario);
    }

    // ACTUALIZAR
    public Usuario actualizar(Long id, Usuario datos) {
        Usuario usuario = buscarPorId(id);

        // Validación para evitar duplicados en el email al actualizar
        if (datos.getEmail() != null
                && !Objects.equals(usuario.getEmail(), datos.getEmail())
                && usuarioRepository.existsByEmail(datos.getEmail())) {
            log.warn("Intento de actualizar usuario ID {} con un email ya existente: {}", id, datos.getEmail());
            throw new RuntimeException("El email ingresado ya pertenece a otro usuario.");
        }

        usuario.setNombre(datos.getNombre());
        usuario.setApellido(datos.getApellido());
        usuario.setEmail(datos.getEmail());

        if (datos.getContrasena() != null && !datos.getContrasena().isBlank()) {
            usuario.setContrasena(datos.getContrasena());
        }

        usuario.setTelefono(datos.getTelefono());
        usuario.setRol(datos.getRol());

        if (datos.getActivo() != null) {
            usuario.setActivo(datos.getActivo());
        }

        return usuarioRepository.save(usuario);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        buscarPorId(id);
        log.info("Eliminando usuario con ID: {}", id);
        usuarioRepository.deleteById(id);
    }
}