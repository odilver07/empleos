package com.lopez.empleos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lopez.empleos.model.Vacante;

@Service
public class VacantesServiceI implements IVacantesService{
	
	private List<Vacante> lista  = null;
	
	public VacantesServiceI() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacante>();
		
		try {
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setDescripcion("Trabajar en la obra");
			vacante1.setFecha(sdf.parse("01-02-2010"));
			vacante1.setNombre("Ing civil");
			vacante1.setSalario(9000.0);
			vacante1.setDestacado(0);
			vacante1.setEstatus("aprobada");
			vacante1.setImagen("empresa1.png");
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setDescripcion("Curar gente");
			vacante2.setFecha(sdf.parse("02-02-2020"));
			vacante2.setNombre("doctor simi");
			vacante2.setSalario(18000.0);
			vacante2.setEstatus("aprobada");
			vacante2.setDestacado(1);
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setDescripcion("Sacarle el diablo a las personas");
			vacante3.setFecha(sdf.parse("02-02-2019"));
			vacante3.setNombre("Exorcista");
			vacante3.setSalario(1500.0);
			vacante3.setDestacado(1);
			vacante3.setEstatus("aprobada");
			vacante3.setImagen("empresa2.png");
			
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			
		}catch(ParseException e) {
			System.out.println("Error: " + e.getMessage());
			}
	}

	@Override
	public List<Vacante> buscarTodas() {
		return lista;
	}

	@Override
	public Vacante buscarVacante(Integer idVacante) {
		for(Vacante v : lista) {
			if(v.getId() == idVacante) {
				return v;
			}
		}
		return null; 
	}

	@Override
	public void guardar(Vacante Vacante) {
		lista.add(Vacante);
		
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
