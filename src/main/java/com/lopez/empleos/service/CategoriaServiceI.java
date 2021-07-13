package com.lopez.empleos.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lopez.empleos.model.Categoria;
import com.lopez.empleos.model.Vacante;

@Service
public class CategoriaServiceI  implements ICategoriaService{
	
	private List<Categoria> listaV = null;
	
	
	public CategoriaServiceI() {
		listaV = new LinkedList<Categoria>();
		try {
			Categoria categoria1 = new Categoria();
			categoria1.setId(1);
			categoria1.setDescripcion("area de informatica");
			categoria1.setNombre("informatica");
			
			Categoria categoria2 = new Categoria();
			categoria2.setId(2);
			categoria2.setDescripcion("Construir casas");
			categoria2.setNombre("Construccion");
			
			Categoria categoria3 = new Categoria();
			categoria3.setId(3);
			categoria3.setDescripcion("Crear murales");
			categoria3.setNombre("Artes");
			
			listaV.add(categoria1);
			listaV.add(categoria2);
			listaV.add(categoria3);
			
			
		}
		catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}
	
	@Override
	public void guardar(Categoria categoria) {
		listaV.add(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		return listaV;
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for(Categoria c : listaV) {
			if(c.getId() == idCategoria) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Categoria> buscarTodos(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
