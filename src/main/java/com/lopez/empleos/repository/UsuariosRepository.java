package com.lopez.empleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lopez.empleos.model.Usuario;


public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	Usuario findByUsername(String username);

}
