package com.lopez.empleos.controllers;



import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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


import com.lopez.empleos.model.Usuario;
import com.lopez.empleos.model.Vacante;
import com.lopez.empleos.service.ICategoriaService;
import com.lopez.empleos.service.IUsuarioService;
import com.lopez.empleos.service.IVacantesService;



@Controller
public class HomeControllers {
	
	@Autowired
	private IVacantesService vacanteService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICategoriaService categoriaService;

	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = vacanteService.buscarTodas();
		model.addAttribute("vacantes", lista);
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostarDetalles(Model model) {
		Vacante vacantes = new Vacante();
		vacantes.setNombre("Ingeniero el telematica");
		vacantes.setDescripcion("Dar mantenimiento a sistemas");
		vacantes.setFecha(new Date());
		vacantes.setSalario(9000.0);
		model.addAttribute("vacante", vacantes);
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String listaTrabajos(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Inginiero en sistemas");
		lista.add("Auxiliar contable");
		lista.add("Vendedor");
		lista.add("Doctor");
		model.addAttribute("empleo", lista);
		return "listado";
	}
	
	
	@GetMapping("/")
	public String vista1(Model model) {
		//List<Vacante> lista = vacanteService.buscarDestacadas();
		//model.addAttribute("vacantes", lista);
		return "home";
	}
	
	@GetMapping("/index")
	public String index(Authentication auth,HttpSession session) {
		//obtenemos el usuario
		String nombre = auth.getName();
		System.out.println("El nombre es: " + nombre);
		//obtenemos los datos del usuario
		for(GrantedAuthority rol : auth.getAuthorities()) {
			System.out.println(rol.getAuthority());
		}
		//si el usuario no existe se crea uno
		if (session.getAttribute("usuario") == null){
			Usuario usuario = usuarioService.buscarPorUsername(nombre);
			usuario.setPassword(null);
			System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		return "redirect:/"; 
	}
	
	
	//User controller 
	@GetMapping("/users")
	public String listaUsuarios(Model model) {
		model.addAttribute("usuario", usuarioService.buscarTodos());
		return "usuarios/listUsuario";
	}
	
	
	@GetMapping("/signup")
	public String usuarioCreate(Usuario usuario) {
		return "formRegistro";
	}
	
	@PostMapping("/signup")
	public String guardarUsuario(Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println("ocurrio el error: " + error);
			}
		}
		String cont = usuario.getPassword();
		String passW = "{noop}"+cont;
		usuario.setPassword(passW);
		System.out.println(usuario);
		usuarioService.guardar(usuario);
		return "/login";
	}
	
	@GetMapping("user/delete/{id}")
	public String eliminarUsuario(@PathVariable("id") int idUsuario) {
		usuarioService.eliminar(idUsuario);
		return "redirect:/users";
	}
	
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
		System.out.println("Buscando la vista home" + vacante);
		
		ExampleMatcher matcher = ExampleMatcher.
				//where descripcion like '%?%'
				matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Vacante> example = Example.of(vacante,matcher);
		List<Vacante> lista = vacanteService.buscarByExample(example);
		model.addAttribute("vacantes", lista);
		return "home";
	}
	
	/*
	 * InitBinder
	 * @param binder
	 * */
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	@ModelAttribute
	public void setGenericos(Model model) {
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.reset();
		model.addAttribute("vacantes", vacanteService.buscarDestacadas());
		model.addAttribute("categoria", categoriaService.buscarTodas());
		model.addAttribute("search",vacanteSearch);
	}

}
