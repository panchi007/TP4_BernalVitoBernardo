package ar.edu.unju.fi.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.collections.MateriaCollection;
import ar.edu.unju.fi.collections.DocenteCollection;
import ar.edu.unju.fi.collections.CarreraCollection;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;

@Controller
public class MateriasController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Docente.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				Docente docente = DocenteCollection.listarDocentes().stream().filter(d -> d.getId().equals(id))
						.findFirst().orElse(null);
				setValue(docente);
			}
		});

		binder.registerCustomEditor(Carrera.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String id) {
				Carrera carrera = CarreraCollection.listarCarreras().stream().filter(c -> c.getCodigo().equals(id))
						.findFirst().orElse(null);
				setValue(carrera);
			}
		});
	}

	@GetMapping("/listaDeMaterias")
	public ModelAndView listarMaterias() {
		List<Materia> materias = MateriaCollection.listarMaterias();
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		modelView.addObject("materiaListado", materias);
		return modelView;
	}

	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {
		ModelAndView modelView = new ModelAndView("formMaterias");
		modelView.addObject("nuevaMateria", new Materia());
		modelView.addObject("docentes", DocenteCollection.listarDocentes());
		modelView.addObject("carreras", CarreraCollection.listarCarreras());
		return modelView;
	}

	@GetMapping("/editarMateria/{codigo}")
	public ModelAndView getFormEditarMateria(@PathVariable String codigo) {
		Materia materiaParaModificar = MateriaCollection.buscarMateriaPorCodigo(codigo);
		if (materiaParaModificar == null) {
			return new ModelAndView("redirect:/listaDeMaterias");
		}
		ModelAndView modelView = new ModelAndView("formMaterias");
		modelView.addObject("nuevaMateria", materiaParaModificar);
		modelView.addObject("docentes", DocenteCollection.listarDocentes());
		modelView.addObject("carreras", CarreraCollection.listarCarreras());
		return modelView;
	}

	@PostMapping("/guardarMateria")
	public String saveMateria(@ModelAttribute("nuevaMateria") Materia materiaParaGuardar,
			RedirectAttributes redirectAttributes) {
		MateriaCollection.agregarMateria(materiaParaGuardar);
		redirectAttributes.addFlashAttribute("mensaje", "La materia ha sido agregada exitosamente.");
		return "redirect:/listaDeMaterias";
	}

	@PostMapping("/modificarMateria")
	public String saveMateriaModificada(@ModelAttribute("nuevaMateria") Materia materiaModificada,
			RedirectAttributes redirectAttributes) {
		System.out.println("Datos recibidos para modificar: " + materiaModificada); // Línea de depuración
		Materia materiaExistente = MateriaCollection.buscarMateriaPorCodigo(materiaModificada.getCodigo());
		if (materiaExistente != null) {
			materiaExistente.setNombre(materiaModificada.getNombre());
			materiaExistente.setCurso(materiaModificada.getCurso());
			materiaExistente.setCantidadHoras(materiaModificada.getCantidadHoras());
			materiaExistente.setModalidad(materiaModificada.getModalidad());
			materiaExistente.setDocente(materiaModificada.getDocente());
			materiaExistente.setCarrera(materiaModificada.getCarrera());
			MateriaCollection.modificarMateria(materiaExistente);
		}
		redirectAttributes.addFlashAttribute("mensaje", "La materia ha sido modificada exitosamente.");
		return "redirect:/listaDeMaterias";
	}

	@GetMapping("/borrarMateria/{codigo}")
	public String deleteMateria(@PathVariable String codigo, RedirectAttributes redirectAttributes) {
		MateriaCollection.eliminarMateria(codigo);
		redirectAttributes.addFlashAttribute("mensaje", "La materia ha sido eliminada exitosamente.");
		return "redirect:/listaDeMaterias";
	}
}
