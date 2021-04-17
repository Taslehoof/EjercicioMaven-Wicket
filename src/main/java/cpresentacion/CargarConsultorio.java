package cpresentacion;

import java.util.List;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.CollectionModel;
import org.apache.wicket.model.util.ListModel;

import cdominio.*;
import cservicio.IServicioConsultorio;

public class CargarConsultorio extends Principal implements IServicioConsultorio {

	private Consultorio consultorio = new Consultorio();

	List<Paciente> pacientes = (List<Paciente>) repositorioPaciente.listar();

	List<Medico> medicos = (List<Medico>) repositorioMedico.listar();

	private DropDownChoice<Medico> ddc;

	public CargarConsultorio(Consultorio consultorioCargado) {

		if (consultorioCargado != null) {
			consultorio = consultorioCargado;
		}
		init();
	}

	public CargarConsultorio() {
		init();
	}

	private void init() {
		Form formulario = new Form("form") {
			@Override
			protected void onSubmit() {
				if (consultorio.getId() != null) {
					repositorio.actualizar(consultorio);
				} else {
					repositorio.cargar(consultorio);
				}
				setResponsePage(ListarConsultorio.class);
			}
		};
		
		formulario.add(new TextField<Medico>("nombre", new PropertyModel<Medico>(consultorio, "nombre")));

		ddc = new DropDownChoice<Medico>("medico", new PropertyModel<Medico>(consultorio, "medico"),
				new LoadableDetachableModel<List<Medico>>() {
					@Override
					protected List<Medico> load() {
						return medicos;
					}
				});

		formulario.add(ddc);		
		
		Palette<Paciente> palette = new Palette<Paciente>(
				"palette",
				new ListModel<Paciente>(consultorio.getPacientes()),
				new CollectionModel<Paciente>(pacientes),
				new ChoiceRenderer<Paciente>("nombreCompleto", "id"),
				10,
				false);

		formulario.add(palette);
		
		add(formulario);
	}
}
