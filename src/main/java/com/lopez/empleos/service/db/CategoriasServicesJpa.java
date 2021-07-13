package com.lopez.empleos.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.lopez.empleos.model.Categoria;
import com.lopez.empleos.repository.CategoriasRepository;
import com.lopez.empleos.service.ICategoriaService;

@Service
@Primary
public class CategoriasServicesJpa implements ICategoriaService {
	
	@Autowired
	private CategoriasRepository categoriaRepo;

	@Override
	public void guardar(Categoria categoria) {
		categoriaRepo.save(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriaRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> op = categoriaRepo.findById(idCategoria);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		categoriaRepo.deleteById(idCategoria);
	}

	@Override
	public Page<Categoria> buscarTodos(Pageable page) {
		return categoriaRepo.findAll(page);
	}
	
	
	

}
