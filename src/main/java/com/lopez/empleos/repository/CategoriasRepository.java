package com.lopez.empleos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lopez.empleos.model.Categoria;

//
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
