package com.zup.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.desafio.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
