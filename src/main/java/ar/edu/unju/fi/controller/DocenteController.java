package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.collections.DocenteCollection;
import ar.edu.unju.fi.model.Docente;

@Controller
public class DocenteController {

    @GetMapping("/listaDeDocentes")
    public ModelAndView listarDocentes() {
        List<Docente> docentes = DocenteCollection.listarDocentes();
        ModelAndView modelView = new ModelAndView("listaDeDocentes");
        modelView.addObject("docenteListado", docentes);
        return modelView;
    }

    @GetMapping("/formularioDocente")
    public ModelAndView getFormDocente() {
        return new ModelAndView("formDocente", "nuevoDocente", new Docente());
    }

    @PostMapping("/guardarDocente")
    public String saveDocente(@ModelAttribute("nuevoDocente") Docente docenteParaGuardar, RedirectAttributes redirectAttributes) {
        DocenteCollection.agregarDocente(docenteParaGuardar);
        redirectAttributes.addFlashAttribute("mensaje", "El docente ha sido agregado exitosamente.");
        return "redirect:/listaDeDocentes";
    }

    @GetMapping("/borrarDocente/{legajo}")
    public String deleteDocente(@PathVariable String legajo, RedirectAttributes redirectAttributes) {
        DocenteCollection.eliminarDocente(legajo);
        redirectAttributes.addFlashAttribute("mensaje", "El docente ha sido eliminado exitosamente.");
        return "redirect:/listaDeDocentes";
    }

    @GetMapping("/editarDocente/{legajo}")
    public ModelAndView getFormEditarDocente(@PathVariable String legajo) {
        Docente docenteParaModificar = DocenteCollection.buscarDocentePorLegajo(legajo);
        ModelAndView modelView = new ModelAndView("formDocente");
        modelView.addObject("nuevoDocente", docenteParaModificar);
        return modelView;
    }

    @PostMapping("/modificarDocente")
    public String saveDocenteModificado(@ModelAttribute("nuevoDocente") Docente docenteModificado, RedirectAttributes redirectAttributes) {
        DocenteCollection.modificarDocente(docenteModificado);
        redirectAttributes.addFlashAttribute("mensaje", "El docente ha sido modificado exitosamente.");
        return "redirect:/listaDeDocentes";
    }
}
