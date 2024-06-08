package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.model.Carrera;

public class CarreraCollection {

    private static List<Carrera> carreras = new ArrayList<>();

    // Método para listar carreras
    public static List<Carrera> listarCarreras() {
        return new ArrayList<>(carreras);  // Devolver una copia para evitar modificaciones externas
    }

    // Método para agregar una carrera
    public static void agregarCarrera(Carrera carrera) {
        if (buscarCarreraPorCodigo(carrera.getCodigo()) == null) {
            carreras.add(carrera);
        } else {
            System.out.println("Carrera con código " + carrera.getCodigo() + " ya existe.");
        }
    }

    // Método para buscar una carrera por código
    public static Carrera buscarCarreraPorCodigo(String codigo) {
        return carreras.stream()
                       .filter(c -> c.getCodigo().equals(codigo))
                       .findFirst()
                       .orElse(null);
    }

    // Método para modificar una carrera
    public static void modificarCarrera(Carrera carreraModificada) {
        for (int i = 0; i < carreras.size(); i++) {
            Carrera carrera = carreras.get(i);
            if (carrera.getCodigo().equals(carreraModificada.getCodigo())) {
                carrera.setNombre(carreraModificada.getNombre());
                carrera.setCantidadAnios(carreraModificada.getCantidadAnios());
                carrera.setEstado(carreraModificada.getEstado());
                return;
            }
        }
        System.out.println("Carrera con código " + carreraModificada.getCodigo() + " no encontrada.");
    }

    // Método para eliminar una carrera
    public static void eliminarCarrera(String codigo) {
        carreras.removeIf(carrera -> carrera.getCodigo().equals(codigo));
    }
}
