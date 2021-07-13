package com.lopez.empleos.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lopez.empleos.model.Usuario;
import com.lopez.empleos.repository.UsuariosRepository;
import com.lopez.empleos.service.IUsuarioService;

@Service
public class UsuariosServiceJPA implements IUsuarioService {
	
	@Autowired
	private UsuariosRepository userRepo;

	@Override
	public void guardar(Usuario usuario) {
		userRepo.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) {
		userRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return userRepo.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
