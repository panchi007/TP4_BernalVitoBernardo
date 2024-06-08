package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.fi.model.Materia;

public class MateriaCollection {

	private static List<Materia> materias = new ArrayList<>();

	// Método para listar materias
	public static List<Materia> listarMaterias() {
		return new ArrayList<>(materias); // Devolver una copia para evitar modificaciones externas
	}

	// Método para buscar una materia por código
	public static Materia buscarMateriaPorCodigo(String codigo) {
		return materias.stream().filter(m -> m.getCodigo().equals(codigo)).findFirst().orElse(null);
	}

	// Método para agregar una materia
	public static void agregarMateria(Materia materia) {
		if (buscarMateriaPorCodigo(materia.getCodigo()) == null) {
			materias.add(materia);
		} else {
			System.out.println("Materia con código " + materia.getCodigo() + " ya existe.");
		}
	}

	// Método para modificar una materia
	public static void modificarMateria(Materia materiaModificada) {
		for (int i = 0; i < materias.size(); i++) {
			Materia materia = materias.get(i);
			if (materia.getCodigo().equals(materiaModificada.getCodigo())) {
				materia.setNombre(materiaModificada.getNombre());
				materia.setCurso(materiaModificada.getCurso());
				materia.setCantidadHoras(materiaModificada.getCantidadHoras());
				materia.setModalidad(materiaModificada.getModalidad());
				materia.setDocente(materiaModificada.getDocente());
				materia.setCarrera(materiaModificada.getCarrera());
				System.out.println("Materia actualizada: " + materia); // Línea de depuración
				return;
			}
		}
		System.out.println("Materia con código " + materiaModificada.getCodigo() + " no encontrada.");
	}

	// Método para eliminar una materia
	public static void eliminarMateria(String codigo) {
		materias.removeIf(materia -> materia.getCodigo().equals(codigo));
	}
}
