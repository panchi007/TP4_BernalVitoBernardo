package ar.edu.unju.fi.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ar.edu.unju.fi.collections.AlumnoCollection;
import ar.edu.unju.fi.model.Alumno;

@Controller
public class AlumnoController {

    @GetMapping("/listaDeAlumnos")
    public ModelAndView listarAlumnos() {
        List<Alumno> alumnos = AlumnoCollection.listarAlumnos();
        ModelAndView modelView = new ModelAndView("listaDeAlumnos");
        modelView.addObject("alumnoListado", alumnos);
        return modelView;
    }

    @GetMapping("/formularioAlumno")
    public ModelAndView getFormAlumno() {
        return new ModelAndView("formAlumno", "nuevoAlumno", new Alumno());
    }

    @GetMapping("/editarAlumno/{lu}")
    public ModelAndView getFormEditarAlumno(@PathVariable String lu) {
        Alumno alumnoParaModificar = AlumnoCollection.buscarAlumnoPorLU(lu);
        ModelAndView modelView = new ModelAndView("formAlumno");
        modelView.addObject("nuevoAlumno", alumnoParaModificar);
        return modelView;
    }

    @PostMapping("/guardarAlumno")
    public String saveAlumno(@ModelAttribute("nuevoAlumno") Alumno alumnoParaGuardar, RedirectAttributes redirectAttributes) {
        AlumnoCollection.agregarAlumno(alumnoParaGuardar);
        redirectAttributes.addFlashAttribute("mensaje", "El alumno ha sido agregado exitosamente.");
        return "redirect:/listaDeAlumnos";
    }

    @PostMapping("/modificarAlumno")
    public String saveAlumnoModificado(@ModelAttribute("nuevoAlumno") Alumno alumnoModificado, RedirectAttributes redirectAttributes) {
        AlumnoCollection.modificarAlumno(alumnoModificado);
        redirectAttributes.addFlashAttribute("mensaje", "El alumno ha sido modificado exitosamente.");
        return "redirect:/listaDeAlumnos";
    }

    @GetMapping("/borrarAlumno/{lu}")
    public String deleteAlumno(@PathVariable String lu, RedirectAttributes redirectAttributes) {
        AlumnoCollection.eliminarAlumno(lu);
        redirectAttributes.addFlashAttribute("mensaje", "El alumno ha sido eliminado exitosamente.");
        return "redirect:/listaDeAlumnos";
    }
}
