package ar.edu.unju.fi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.collections.CarreraCollection;
import ar.edu.unju.fi.model.Carrera;

@Controller
public class CarreraController {

    @Autowired
    private Carrera nuevaCarrera;

    @GetMapping("/formularioCarrera")
    public ModelAndView getFormCarrera() {
        return new ModelAndView("formCarrera", "nuevaCarrera", nuevaCarrera);
    }

    @PostMapping("/guardarCarrera")
    public String saveCarrera(@ModelAttribute("nuevaCarrera") Carrera carreraParaGuardar) {
        carreraParaGuardar.setEstado(true); // Asume que una carrera nueva siempre está activa
        CarreraCollection.agregarCarrera(carreraParaGuardar);
        return "redirect:/listaDeCarreras";
    }

    @GetMapping("/borrarCarrera/{codigo}")
    public String deleteCarrera(@PathVariable String codigo, RedirectAttributes redirectAttributes) {
        CarreraCollection.eliminarCarrera(codigo);
        redirectAttributes.addFlashAttribute("mensaje", "La carrera ha sido eliminada exitosamente.");
        return "redirect:/listaDeCarreras";
    }

    @GetMapping("/listaDeCarreras")
    public ModelAndView listarCarreras() {
        List<Carrera> carrerasActivas = CarreraCollection.listarCarreras().stream()
                .filter(Carrera::getEstado)
                .collect(Collectors.toList());
        ModelAndView modelView = new ModelAndView("listaDeCarreras");
        modelView.addObject("listadoCarreras", carrerasActivas);
        modelView.addObject("carreraParaModificar", new Carrera());
        return modelView;
    }

    @GetMapping("/editarCarrera/{codigo}")
    public ModelAndView getFormEditarCarrera(@PathVariable String codigo) {
        Carrera carreraParaModificar = CarreraCollection.buscarCarreraPorCodigo(codigo);
        if (carreraParaModificar == null) {
            // Manejar el caso en que no se encuentre la carrera
            return new ModelAndView("redirect:/listaDeCarreras");
        }
        List<Carrera> carrerasActivas = CarreraCollection.listarCarreras().stream()
                .filter(Carrera::getEstado)
                .collect(Collectors.toList());
        ModelAndView modelView = new ModelAndView("listaDeCarreras");
        modelView.addObject("listadoCarreras", carrerasActivas);
        modelView.addObject("carreraParaModificar", carreraParaModificar);
        return modelView;
    }

    @PostMapping("/modificarCarrera")
    public String saveCarreraModificada(@ModelAttribute("carreraParaModificar") Carrera carreraModificada,
            RedirectAttributes redirectAttributes) {
        Carrera carreraExistente = CarreraCollection.buscarCarreraPorCodigo(carreraModificada.getCodigo());
        if (carreraExistente != null) {
            carreraExistente.setNombre(carreraModificada.getNombre());
            carreraExistente.setCantidadAnios(carreraModificada.getCantidadAnios());
            carreraExistente.setEstado(carreraModificada.getEstado());
            CarreraCollection.modificarCarrera(carreraExistente);
        }
        redirectAttributes.addFlashAttribute("mensaje", "La carrera ha sido modificada exitosamente.");
        return "redirect:/listaDeCarreras";
    }
}
