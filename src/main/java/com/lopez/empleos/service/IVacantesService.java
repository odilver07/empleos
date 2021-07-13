package com.lopez.empleos.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lopez.empleos.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarVacante(Integer idVacante);
	void guardar(Vacante Vacante);
	List<Vacante> buscarDestacadas();
	void eliminar(Integer idVacante);
	List<Vacante> buscarByExample(Example<Vacante> example);
	Page<Vacante>buscarTodas(Pageable page);

}
