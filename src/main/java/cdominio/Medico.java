package cdominio;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;


@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Medico extends Persona {

	private int numeroMatricula;
	private TipoEspecialidad especialidad;

	public int getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(int numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public TipoEspecialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(TipoEspecialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Medico() {}

	@Override
	public String toString() {
		return " -> " + nombre + " " + apellido;
	}

}
