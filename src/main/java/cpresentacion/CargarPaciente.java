package cpresentacion;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import cdominio.Paciente;
import cdominio.TipoObraSocial;
import cservicio.IServicioPaciente;

public class CargarPaciente extends Principal implements IServicioPaciente{

	private Paciente paciente = new Paciente();
	private List<TipoObraSocial> listaObraSocial = Arrays.asList(TipoObraSocial.values());

	public CargarPaciente() {
		init();
	}
	
	public CargarPaciente(Paciente pacienteCargado) {

		if (pacienteCargado != null) {
			paciente = pacienteCargado;
		}

		init();
	}

	private void init() {

		CompoundPropertyModel<Paciente> pacienteModel = new CompoundPropertyModel<Paciente>(paciente);

		PropertyModel obraSocialModel = new PropertyModel(pacienteModel, "ObraSocial");

		RadioChoice<TipoObraSocial> tiposObraSocial = new RadioChoice("ObraSocial", obraSocialModel, listaObraSocial);

		Form form = new Form("form", pacienteModel) {

			protected void onSubmit() {
		    	if(paciente.getId() != null) {
		    		repositorio.actualizar(paciente);
		    	}
		    	else {
			    	repositorio.cargar(paciente);
		    	}
		    	setResponsePage(ListarPaciente.class);
			}
		};

		add(form);
		
		form.add(new TextField<String>("nombre"));
		form.add(new TextField<String>("apellido"));
		form.add(new TextField<Integer>("documento", Integer.class));
		form.add(new TextField<Integer>("numeroAfiliado", Integer.class));
		form.add(tiposObraSocial);
	}
}
