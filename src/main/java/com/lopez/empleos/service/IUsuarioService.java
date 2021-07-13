package com.lopez.empleos.service;

import java.util.List;

import com.lopez.empleos.model.Usuario;

public interface IUsuarioService {
	
	void guardar(Usuario usuario);
	void eliminar(Integer idUsurio);
	List<Usuario> buscarTodos();
	Usuario buscarPorUsername(String username);

}
