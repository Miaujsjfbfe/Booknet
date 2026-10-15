package com.booknet.ms_usuarios.Repository;

import com.booknet.ms_usuarios.Model.Roles;
import com.booknet.ms_usuarios.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por email (para login o consultas)
    Optional<Usuario> findByEmail(String email);

    // Verificar si ya existe un usuario registrado con ese email
    boolean existsByEmail(String email);

    // Filtrar usuarios por rol
    List<Usuario> findByRol(Roles rol);

    // Listar solo los usuarios activos
    List<Usuario> findByActivoTrue();
}
