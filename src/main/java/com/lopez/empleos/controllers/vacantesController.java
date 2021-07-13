package com.lopez.empleos.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lopez.empleos.model.Vacante;
import com.lopez.empleos.service.ICategoriaService;
import com.lopez.empleos.service.IVacantesService;
import com.lopez.empleos.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class vacantesController {
	
	//inyectar valores de properties
	@Value("${empleos.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IVacantesService vacanteService;
	
	@Autowired
	//@Qualifier("categoriasServicesJpa")
	private ICategoriaService categoriaService;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> vacante = vacanteService.buscarTodas();
		model.addAttribute("vacantes", vacante);
		return "vacantes/listVacantes";
	}
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndex(Model model, Pageable page) {
		Page<Vacante> lista = vacanteService.buscarTodas(page);
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVacante, Model model) {
		Vacante vacante = vacanteService.buscarVacante(idVacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/formVacante";
	}
	
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		model.addAttribute("categoria", categoriaService.buscarTodas());
		return "vacantes/formVacante";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes red) {
		System.out.println("Borrando usuario con id: " + idVacante);
		red.addFlashAttribute("msg", "La vacante ha sido eliminada xD");
		vacanteService.eliminar(idVacante);
		return "redirect:/vacantes/index";
	}	
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		Vacante vacante = vacanteService.buscarVacante(idVacante);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	
	@PostMapping("/save")
	public String guardar(Vacante Vacante, BindingResult result, RedirectAttributes atributtes, 
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println("ocurrio el error: " + error);
			}
			return "vacantes/formVacante";
		}
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
			// Procesamos la variable nombreImagen
				Vacante.setImagen(nombreImagen);
			}
		}
		vacanteService.guardar(Vacante);
		atributtes.addFlashAttribute("msg", "Vacante guardada");
		System.out.println("Vacante: " + Vacante);
		return "redirect:/vacantes/index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("categoria", categoriaService.buscarTodas());
	}
	
	/*
		@PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion,
			@RequestParam("estatus") String estatus, @RequestParam("fecha") String fecha, @RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario, @RequestParam("detalles") String detalles) {
		
		System.out.println(nombre + " " + descripcion + " " + estatus + " " + fecha + " "  + destacado
				+ salario + " " + detalles);
		return "vacantes/listVacantes";
	}
	 */

}
