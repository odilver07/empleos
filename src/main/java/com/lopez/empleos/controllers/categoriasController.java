package com.lopez.empleos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lopez.empleos.model.Categoria;
import com.lopez.empleos.model.Vacante;
import com.lopez.empleos.service.ICategoriaService;

@Controller
@RequestMapping("/categorias")
public class categoriasController {
	
	@Autowired
	//@Qualifier("categoriasServicesJpa")
	private ICategoriaService categoriaService;
	
	
	@GetMapping(value = "/index")
	public String mostrarPage(Model model, Pageable page) {
		Page<Categoria> lista = categoriaService.buscarTodos(page);
		model.addAttribute("categoria", lista);
		return "listCategorias";
	}
	
	@GetMapping("/create")
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
		}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idCategoria, Model model) {
		Categoria categoria = categoriaService.buscarPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria";
	}
	
	@PostMapping("/save")
	public String guardar(Categoria categoria, BindingResult result,RedirectAttributes redirect) {
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println("ocurrio el error: " + error);
			}
			return "categorias/formCategoria";
		}
		categoriaService.guardar(categoria);
		redirect.addFlashAttribute("msg", "Categoria guardada");
		System.out.println("Categoria: " + categoria);
		return "redirect:/categorias/index";
		}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCategoria) {
		categoriaService.eliminar(idCategoria);
		return "redirect:/categorias/index";
	}

}
