package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.model.Docente;

public class DocenteCollection {
    
    public static List<Docente> docentes = new ArrayList<>();

    // Método para listar docentes
    public static List<Docente> listarDocentes() {
        return new ArrayList<>(docentes);  // Devolver una copia para evitar modificaciones externas
    }

    // Método para buscar un docente por legajo
    public static Docente buscarDocentePorLegajo(String leg) {
        return docentes.stream()
                       .filter(d -> d.getLegajo().equals(leg))
                       .findFirst()
                       .orElse(null);
    }

    // Método para agregar un docente
    public static void agregarDocente(Docente d) {
        if (buscarDocentePorLegajo(d.getLegajo()) == null) {
            docentes.add(d);
        } else {
            System.out.println("Docente con legajo " + d.getLegajo() + " ya existe.");
        }
    }

    // Método para modificar un docente
    public static void modificarDocente(Docente docenteModificado) {
        for (int i = 0; i < docentes.size(); i++) {
            Docente docente = docentes.get(i);
            if (docente.getLegajo().equals(docenteModificado.getLegajo())) {
                // Actualizar todos los campos del docente
                docente.setNombre(docenteModificado.getNombre());
                docente.setApellido(docenteModificado.getApellido());
                docente.setEmail(docenteModificado.getEmail());
                docente.setTelefono(docenteModificado.getTelefono());
                return;
            }
        }
        System.out.println("Docente con legajo " + docenteModificado.getLegajo() + " no encontrado.");
    }

    // Método para eliminar un docente
    public static void eliminarDocente(String leg) {
        docentes.removeIf(docente -> docente.getLegajo().equals(leg));
    }
}
