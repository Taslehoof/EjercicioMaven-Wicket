package cdominio;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;


@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Paciente extends Persona {

	private int numeroAfiliado;
	private TipoObraSocial obraSocial;

	public int getNumeroAfiliado() {
		return numeroAfiliado;
	}

	public void setNumeroAfiliado(int numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}

	public TipoObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(TipoObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	public Paciente(Long id, String nombre, String apellido, int documento, int numeroAfiliado,
			TipoObraSocial obraSocial) {
		super(id, nombre, apellido, documento);
		this.numeroAfiliado = numeroAfiliado;
		this.obraSocial = obraSocial;
	}

	public Paciente() {
	}

	public String toString() {
		return " -> " + nombre + " " + apellido;
	}

	public String getNombreCompleto() {
		return "(" + numeroAfiliado + ") " + nombre + " " + apellido;
	}

}
