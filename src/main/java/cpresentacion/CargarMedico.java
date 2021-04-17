package cpresentacion;


import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import cdominio.Medico;
import cdominio.Paciente;
import cdominio.TipoEspecialidad;
import cdominio.TipoObraSocial;
import cservicio.IServicioMedico;

public class CargarMedico extends Principal implements IServicioMedico{
	
	private Medico medico = new Medico();
	private List<TipoEspecialidad> listaTipoEspecialidad = Arrays.asList(TipoEspecialidad.values());
	
	public CargarMedico(Medico medicoCargado) {
		
		if(medicoCargado != null) {
			medico = medicoCargado;
		}
		init();
	}
	
	public CargarMedico() {
		init();
	}
	
	private void init() {
		
		CompoundPropertyModel<Medico> medicoModel = new CompoundPropertyModel<Medico>(medico);

		PropertyModel tipoEspecialidadModel = new PropertyModel(medicoModel, "especialidad");

		RadioChoice<TipoEspecialidad> especialidad = new RadioChoice("especialidad", tipoEspecialidadModel, listaTipoEspecialidad);

		Form form = new Form("form") {
			@Override
			protected void onSubmit() {
				if (medico.getId() != null) {
					repositorio.actualizar(medico);
				} else {
					repositorio.cargar(medico);
				}
				setResponsePage(ListarMedico.class);
			}
		};

		form.add(new TextField<Medico>("NumeroMatricula", new PropertyModel<Medico>(medico, "NumeroMatricula")));
		form.add(new TextField<Medico>("nombre", new PropertyModel<Medico>(medico, "nombre")));
		form.add(new TextField<Medico>("apellido", new PropertyModel<Medico>(medico, "apellido")));
		form.add(new TextField<Medico>("documento", new PropertyModel<Medico>(medico, "documento")));
		form.add(especialidad);
		add(form);
	}


}
