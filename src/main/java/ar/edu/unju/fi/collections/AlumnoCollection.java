package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unju.fi.model.Alumno;

public class AlumnoCollection {
    
    private static List<Alumno> alumnos = new ArrayList<>();

    // Método para listar alumnos
    public static List<Alumno> listarAlumnos() {
        return new ArrayList<>(alumnos);  // Devolver una copia para evitar modificaciones externas
    }

    // Método para buscar un alumno por LU
    public static Alumno buscarAlumnoPorLU(String lu) {
        return alumnos.stream()
                      .filter(a -> a.getLu().equals(lu))
                      .findFirst()
                      .orElse(null);
    }

    // Método para agregar un alumno
    public static void agregarAlumno(Alumno alumno) {
        if (buscarAlumnoPorLU(alumno.getLu()) == null) {
            alumnos.add(alumno);
        } else {
            System.out.println("Alumno con LU " + alumno.getLu() + " ya existe.");
        }
    }

    // Método para modificar un alumno
    public static void modificarAlumno(Alumno alumnoModificado) {
        for (int i = 0; i < alumnos.size(); i++) {
            Alumno alumno = alumnos.get(i);
            if (alumno.getLu().equals(alumnoModificado.getLu())) {
                alumno.setDni(alumnoModificado.getDni());
                alumno.setNombre(alumnoModificado.getNombre());
                alumno.setApellido(alumnoModificado.getApellido());
                alumno.setEmail(alumnoModificado.getEmail());
                alumno.setTelefono(alumnoModificado.getTelefono());
                alumno.setFechaNacimiento(alumnoModificado.getFechaNacimiento());
                alumno.setDomicilio(alumnoModificado.getDomicilio());
                // No es necesario actualizar el LU ya que es el identificador
                return;
            }
        }
        System.out.println("Alumno con LU " + alumnoModificado.getLu() + " no encontrado.");
    }

    // Método para eliminar un alumno
    public static void eliminarAlumno(String lu) {
        alumnos.removeIf(alumno -> alumno.getLu().equals(lu));
    }
}
