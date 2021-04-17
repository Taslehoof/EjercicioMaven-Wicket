package cdominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Consultorio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey
	private Long id;
	private String nombre;
	
	@Column(name = "MEDICO_ID")
	private Medico medico;
	
	@Persistent(table = "CONSULTORIO_PACIENTES")
	@Join(column = "CONSULTORIO_ID_OID")
	@Element(column = "PACIENTE_ID_EID")
	private List<Paciente> pacientes;
	
	public Consultorio() {
		this.pacientes = new ArrayList<Paciente>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Consultorio(Long id, String nombre, Medico medico, List<Paciente> pacientes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.medico = medico;
		this.pacientes = pacientes;
	}

	@Override
	public String toString() {
		return "Consultorio [id=" + id + ", nombre=" + nombre + ", medico=" + medico + ", pacientes=" + pacientes + "]";
	}

}
