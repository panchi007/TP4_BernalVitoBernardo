package ar.edu.unju.fi.model;

public class Materia {
	private String codigo;
	private String nombre;
	private String curso;
	private int cantidadHoras;
	private String modalidad;
	private Docente docente;
	private Carrera carrera;

	// Constructor vacío
	public Materia() {
	}

	// Getters y Setters
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getCantidadHoras() {
		return cantidadHoras;
	}

	public void setCantidadHoras(int cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	// Método toString para depuración
	@Override
	public String toString() {
		return "Materia{" + "codigo='" + codigo + '\'' + ", nombre='" + nombre + '\'' + ", curso='" + curso + '\''
				+ ", cantidadHoras=" + cantidadHoras + ", modalidad='" + modalidad + '\'' + ", docente="
				+ (docente != null ? docente.getNombre() + ' ' + docente.getApellido() : "null") + ", carrera="
				+ (carrera != null ? carrera.getNombre() : "null") + '}';
	}
}
