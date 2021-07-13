package com.lopez.empleos.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.lopez.empleos.model.Vacante;
import com.lopez.empleos.repository.VacantesRepository;
import com.lopez.empleos.service.IVacantesService;

@Service
@Primary
public class VacantesServiceJPA implements IVacantesService {
	
	@Autowired
	private VacantesRepository vacanteRepo;

	@Override
	public List<Vacante> buscarTodas() {
		return vacanteRepo.findAll();
	}

	@Override
	public Vacante buscarVacante(Integer idVacante) {
		Optional<Vacante> op = vacanteRepo.findById(idVacante);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante Vacante) {
		vacanteRepo.save(Vacante);
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		return vacanteRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public void eliminar(Integer idVacante) {
		vacanteRepo.deleteById(idVacante);
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		return vacanteRepo.findAll(example);
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		return vacanteRepo.findAll(page);
	}

}
