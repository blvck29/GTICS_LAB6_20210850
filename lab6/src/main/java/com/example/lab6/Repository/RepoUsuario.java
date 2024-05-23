package com.example.lab6.Repository;

import com.example.lab6.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario, Integer> {
    public Usuario findByCorreo (String correo);
}
